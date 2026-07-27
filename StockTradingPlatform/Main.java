import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        User user = new User("John Doe", 100000.00);
        Portfolio portfolio = new Portfolio(user);
        List<Transaction> transactions = new ArrayList<>();

        System.out.println("============================================");
        System.out.println("     WELCOME TO STOCK TRADING PLATFORM");
        System.out.println("============================================");

        FileManager.loadData(portfolio, transactions, market);

        System.out.println("Welcome, " + user.getUsername() + "!");
        System.out.println("Your current balance: " + Utils.formatCurrency(user.getBalance()));

        int choice;
        do {
            displayMenu();
            choice = Utils.readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> viewStocks(market);
                case 2 -> buyStock(market, portfolio, user, transactions);
                case 3 -> sellStock(market, portfolio, user, transactions);
                case 4 -> portfolio.displayPortfolio();
                case 5 -> viewBalance(user);
                case 6 -> viewTransactionHistory(transactions);
                case 7 -> FileManager.saveData(portfolio, transactions);
                case 8 -> reloadData(portfolio, transactions, market);
                case 9 -> exitApplication();
                default -> System.out.println("Invalid choice! Please enter a number between 1 and 9.");
            }
            if (choice != 9) {
                Utils.pressEnterToContinue();
            }
        } while (choice != 9);
    }

    private static void displayMenu() {
        System.out.println("\n============================================");
        System.out.println("            MAIN MENU");
        System.out.println("============================================");
        System.out.println("  1. View Stocks");
        System.out.println("  2. Buy Stock");
        System.out.println("  3. Sell Stock");
        System.out.println("  4. View Portfolio");
        System.out.println("  5. View Balance");
        System.out.println("  6. Transaction History");
        System.out.println("  7. Save Data");
        System.out.println("  8. Load Data");
        System.out.println("  9. Exit");
        System.out.println("============================================");
    }

    private static void viewStocks(StockMarket market) {
        market.displayStocks();
    }

    private static void buyStock(StockMarket market, Portfolio portfolio, User user, List<Transaction> transactions) {
        market.displayStocks();
        System.out.println();

        int stockId = Utils.readPositiveInt("Enter Stock ID to buy: ");
        Stock selectedStock = market.getStockById(stockId);

        if (selectedStock == null) {
            System.out.println("Invalid Stock ID. Please try again.");
            return;
        }

        int quantity = Utils.readPositiveInt("Enter quantity to buy: ");
        double totalCost = quantity * selectedStock.getCurrentPrice();

        System.out.printf("Stock: %s | Price per share: ₹%.2f | Total cost: ₹%.2f%n",
                selectedStock.getStockName(), selectedStock.getCurrentPrice(), totalCost);

        if (!user.hasSufficientBalance(totalCost)) {
            System.out.println("Insufficient balance! You need " + Utils.formatCurrency(totalCost)
                    + " but you have " + Utils.formatCurrency(user.getBalance()));
            return;
        }

        user.deductBalance(totalCost);
        portfolio.addStock(selectedStock, quantity, selectedStock.getCurrentPrice());

        Transaction t = new Transaction(selectedStock.getStockName(), "BUY", quantity, selectedStock.getCurrentPrice());
        transactions.add(t);

        System.out.println("Successfully bought " + quantity + " shares of " + selectedStock.getStockName()
                + " for " + Utils.formatCurrency(totalCost));
    }

    private static void sellStock(StockMarket market, Portfolio portfolio, User user, List<Transaction> transactions) {
        if (portfolio.getHoldings().isEmpty()) {
            System.out.println("You don't own any stocks to sell.");
            return;
        }

        portfolio.displayPortfolio();
        System.out.println();

        String stockName = Utils.readString("Enter stock name to sell: ");

        if (!portfolio.hasStock(stockName)) {
            System.out.println("You don't own any shares of " + stockName);
            return;
        }

        Stock stock = market.getStockByName(stockName);
        if (stock == null) {
            System.out.println("Stock not found in market.");
            return;
        }

        int ownedQty = portfolio.getStockQuantity(stockName);
        System.out.println("You own " + ownedQty + " shares of " + stockName
                + " at current price " + Utils.formatCurrency(stock.getCurrentPrice()));

        int quantity = Utils.readPositiveInt("Enter quantity to sell: ");

        if (quantity > ownedQty) {
            System.out.println("You only own " + ownedQty + " shares. Cannot sell " + quantity + ".");
            return;
        }

        double totalAmount = quantity * stock.getCurrentPrice();

        if (portfolio.removeStock(stockName, quantity)) {
            user.addBalance(totalAmount);

            Transaction t = new Transaction(stockName, "SELL", quantity, stock.getCurrentPrice());
            transactions.add(t);

            System.out.println("Successfully sold " + quantity + " shares of " + stockName
                    + " for " + Utils.formatCurrency(totalAmount));
        } else {
            System.out.println("Failed to sell stock. Please try again.");
        }
    }

    private static void viewBalance(User user) {
        System.out.println("============================================");
        System.out.println("          ACCOUNT BALANCE");
        System.out.println("============================================");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Balance:  " + Utils.formatCurrency(user.getBalance()));
        System.out.println("============================================");
    }

    private static void viewTransactionHistory(List<Transaction> transactions) {
        System.out.println("============================================");
        System.out.println("       TRANSACTION HISTORY");
        System.out.println("============================================");
        if (transactions.isEmpty()) {
            System.out.println("  No transactions yet.");
        } else {
            System.out.printf("%-8s %-20s %-15s %-5s %-8s %-12s%n",
                    "TXN ID", "Date & Time", "Stock", "Type", "Qty", "Price");
            System.out.println("--------------------------------------------------------------------");
            for (Transaction t : transactions) {
                System.out.printf("%-8s %-20s %-15s %-5s %-8d ₹%-10.2f%n",
                        t.getTransactionId(),
                        t.getDateTime().format(java.time.format.DateTimeFormatter.ofPattern("dd-MMM HH:mm")),
                        t.getStockName(),
                        t.getType(),
                        t.getQuantity(),
                        t.getPrice());
            }
        }
        System.out.println("============================================");
    }

    private static void reloadData(Portfolio portfolio, List<Transaction> transactions, StockMarket market) {
        portfolio.getHoldings().clear();
        transactions.clear();
        FileManager.loadData(portfolio, transactions, market);
        System.out.println("Data loaded successfully. Current balance: "
                + Utils.formatCurrency(portfolio.getUser().getBalance()));
    }

    private static void exitApplication() {
        System.out.println("\n============================================");
        System.out.println("  Thank you for using Stock Trading Platform!");
        System.out.println("============================================");
        scanner.close();
    }
}
