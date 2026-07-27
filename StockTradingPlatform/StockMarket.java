import java.util.*;

public class StockMarket {
    private List<Stock> stocks;
    private Map<String, Stock> stockMap;

    public StockMarket() {
        stocks = new ArrayList<>();
        stockMap = new HashMap<>();
        initializeStocks();
    }

    private void initializeStocks() {
        addStock(1, "TCS", 3850.00);
        addStock(2, "Infosys", 1620.00);
        addStock(3, "Reliance", 2950.00);
        addStock(4, "Wipro", 450.00);
        addStock(5, "HDFC Bank", 1680.00);
        addStock(6, "ICICI Bank", 1120.00);
        addStock(7, "Adani Ports", 1250.00);
        addStock(8, "Larsen & Toubro", 3450.00);
        addStock(9, "SBI", 780.00);
        addStock(10, "Tata Motors", 620.00);
    }

    private void addStock(int id, String name, double price) {
        Stock stock = new Stock(id, name, price);
        stocks.add(stock);
        stockMap.put(name, stock);
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public Stock getStockByName(String name) {
        return stockMap.get(name);
    }

    public Stock getStockById(int id) {
        for (Stock s : stocks) {
            if (s.getStockId() == id) {
                return s;
            }
        }
        return null;
    }

    public void displayStocks() {
        System.out.println("=====================================");
        System.out.println("        AVAILABLE STOCKS");
        System.out.println("=====================================");
        System.out.printf("%-5s %-22s %s%n", "ID", "Stock", "Price");
        System.out.println("-------------------------------------");
        for (Stock s : stocks) {
            System.out.printf("%-5d %-22s ₹%.2f%n",
                s.getStockId(), s.getStockName(), s.getCurrentPrice());
        }
        System.out.println("=====================================");
    }
}
