import java.util.*;

public class Portfolio {
    private User user;
    private Map<String, StockHolding> holdings;

    public static class StockHolding {
        private Stock stock;
        private int quantity;
        private double purchasePrice;

        public StockHolding(Stock stock, int quantity, double purchasePrice) {
            this.stock = stock;
            this.quantity = quantity;
            this.purchasePrice = purchasePrice;
        }

        public Stock getStock() {
            return stock;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(double purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public double getTotalInvestment() {
            return quantity * purchasePrice;
        }

        public double getCurrentValue() {
            return quantity * stock.getCurrentPrice();
        }

        public double getProfitLoss() {
            return (stock.getCurrentPrice() - purchasePrice) * quantity;
        }

        public double getProfitLossPercent() {
            if (purchasePrice == 0) return 0;
            return ((stock.getCurrentPrice() - purchasePrice) / purchasePrice) * 100;
        }
    }

    public Portfolio(User user) {
        this.user = user;
        this.holdings = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public Map<String, StockHolding> getHoldings() {
        return holdings;
    }

    public void addStock(Stock stock, int quantity, double price) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        String name = stock.getStockName();
        if (holdings.containsKey(name)) {
            StockHolding h = holdings.get(name);
            int newQty = h.getQuantity() + quantity;
            double newAvgPrice = ((h.getQuantity() * h.getPurchasePrice()) + (quantity * price)) / newQty;
            h.setQuantity(newQty);
            h.setPurchasePrice(newAvgPrice);
        } else {
            holdings.put(name, new StockHolding(stock, quantity, price));
        }
    }

    public boolean removeStock(String stockName, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        if (!holdings.containsKey(stockName)) {
            return false;
        }
        StockHolding h = holdings.get(stockName);
        if (h.getQuantity() < quantity) {
            return false;
        }
        int newQty = h.getQuantity() - quantity;
        if (newQty == 0) {
            holdings.remove(stockName);
        } else {
            h.setQuantity(newQty);
        }
        return true;
    }

    public boolean hasStock(String stockName) {
        return holdings.containsKey(stockName);
    }

    public int getStockQuantity(String stockName) {
        StockHolding h = holdings.get(stockName);
        return h == null ? 0 : h.getQuantity();
    }

    public double getTotalInvestment() {
        double total = 0;
        for (StockHolding h : holdings.values()) {
            total += h.getTotalInvestment();
        }
        return total;
    }

    public double getCurrentValue() {
        double total = 0;
        for (StockHolding h : holdings.values()) {
            total += h.getCurrentValue();
        }
        return total;
    }

    public double getOverallProfitLoss() {
        return getCurrentValue() - getTotalInvestment();
    }

    public double getOverallProfitLossPercent() {
        double totalInvestment = getTotalInvestment();
        if (totalInvestment == 0) return 0;
        return (getOverallProfitLoss() / totalInvestment) * 100;
    }

    public void displayPortfolio() {
        System.out.println("============================================");
        System.out.println("            PORTFOLIO");
        System.out.println("============================================");
        System.out.printf("%-20s %-6s %-12s %-12s %-12s%n", "Stock", "Qty", "Buy Price", "Curr Price", "P/L");
        System.out.println("--------------------------------------------");
        if (holdings.isEmpty()) {
            System.out.println("  No stocks in portfolio.");
        } else {
            for (StockHolding h : holdings.values()) {
                double pl = h.getProfitLoss();
                String plStr = String.format("₹%.2f (%+.2f%%)", pl, h.getProfitLossPercent());
                System.out.printf("%-20s %-6d ₹%-10.2f ₹%-10.2f %s%n",
                    h.getStock().getStockName(),
                    h.getQuantity(),
                    h.getPurchasePrice(),
                    h.getStock().getCurrentPrice(),
                    plStr);
            }
        }
        System.out.println("--------------------------------------------");
        System.out.printf("%-20s %s%n", "Total Investment:", String.format("₹%.2f", getTotalInvestment()));
        System.out.printf("%-20s %s%n", "Current Value:", String.format("₹%.2f", getCurrentValue()));
        System.out.printf("%-20s %s%n", "Overall P/L:", String.format("₹%.2f (%+.2f%%)", getOverallProfitLoss(), getOverallProfitLossPercent()));
        System.out.println("============================================");
    }
}
