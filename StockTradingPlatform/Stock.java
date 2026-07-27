public class Stock {
    private int stockId;
    private String stockName;
    private double currentPrice;

    public Stock(int stockId, String stockName, double currentPrice) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.currentPrice = currentPrice;
    }

    public Stock(String stockName, double currentPrice) {
        this.stockId = -1;
        this.stockName = stockName;
        this.currentPrice = currentPrice;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-22s ₹%.2f", stockId, stockName, currentPrice);
    }
}
