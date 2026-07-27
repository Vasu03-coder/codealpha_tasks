import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileManager {

    private static final String PORTFOLIO_FILE = "portfolio.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void saveData(Portfolio portfolio, List<Transaction> transactions) {
        savePortfolio(portfolio);
        saveTransactions(transactions);
        System.out.println("Data saved successfully.");
    }

    private static void savePortfolio(Portfolio portfolio) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PORTFOLIO_FILE))) {
            User user = portfolio.getUser();
            pw.println(user.getUsername() + "|" + String.format("%.2f", user.getBalance()));
            for (Portfolio.StockHolding h : portfolio.getHoldings().values()) {
                pw.println(h.getStock().getStockId() + "|"
                        + h.getStock().getStockName() + "|"
                        + h.getQuantity() + "|"
                        + String.format("%.2f", h.getPurchasePrice()));
            }
        } catch (IOException e) {
            System.out.println("Error saving portfolio: " + e.getMessage());
        }
    }

    private static void saveTransactions(List<Transaction> transactions) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TRANSACTIONS_FILE))) {
            pw.println(Transaction.getIdCounter());
            for (Transaction t : transactions) {
                pw.println(t.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public static void loadData(Portfolio portfolio, List<Transaction> transactions, StockMarket market) {
        loadPortfolio(portfolio, market);
        loadTransactions(transactions);
    }

    private static void loadPortfolio(Portfolio portfolio, StockMarket market) {
        File file = new File(PORTFOLIO_FILE);
        if (!file.exists()) {
            System.out.println("No saved portfolio found. Starting fresh.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null) return;
            String[] parts = line.split("\\|");
            if (parts.length >= 2) {
                portfolio.getUser().setUsername(parts[0]);
                portfolio.getUser().setBalance(Double.parseDouble(parts[1]));
            }
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                parts = line.split("\\|");
                if (parts.length >= 4) {
                    int stockId = Integer.parseInt(parts[0]);
                    String stockName = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    Stock stock = market.getStockByName(stockName);
                    if (stock == null) {
                        stock = new Stock(stockId, stockName, price);
                        market.getStocks().add(stock);
                    }
                    portfolio.getHoldings().put(stockName,
                        new Portfolio.StockHolding(stock, quantity, price));
                }
            }
            System.out.println("Portfolio loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading portfolio: " + e.getMessage());
        }
    }

    private static void loadTransactions(List<Transaction> transactions) {
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null) return;
            Transaction.setIdCounter(Integer.parseInt(line.trim()));
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                transactions.add(Transaction.fromFileString(line));
            }
            System.out.println("Transaction history loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
}
