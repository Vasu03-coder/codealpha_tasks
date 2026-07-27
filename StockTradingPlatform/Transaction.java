import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int idCounter = 0;
    private String transactionId;
    private LocalDateTime dateTime;
    private String stockName;
    private String type;
    private int quantity;
    private double price;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Transaction(String stockName, String type, int quantity, double price) {
        idCounter++;
        this.transactionId = String.format("TXN%03d", idCounter);
        this.dateTime = LocalDateTime.now();
        this.stockName = stockName;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public Transaction(String transactionId, LocalDateTime dateTime, String stockName,
                      String type, int quantity, double price) {
        this.transactionId = transactionId;
        this.dateTime = dateTime;
        this.stockName = stockName;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStockName() {
        return stockName;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int counter) {
        idCounter = counter;
    }

    public double getTotalAmount() {
        return quantity * price;
    }

    public String toFileString() {
        return transactionId + "|" + dateTime.format(FORMATTER) + "|" + stockName + "|" + type + "|" + quantity + "|" + String.format("%.2f", price);
    }

    public static Transaction fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new Transaction(
            parts[0],
            LocalDateTime.parse(parts[1], FORMATTER),
            parts[2],
            parts[3],
            Integer.parseInt(parts[4]),
            Double.parseDouble(parts[5])
        );
    }

    public String toDisplayString() {
        return String.format("%-7s %-20s %-10s %-5s %-6d ₹%-10.2f",
            transactionId, stockName, type, dateTime.format(DateTimeFormatter.ofPattern("dd-MMM")), quantity, price);
    }
}
