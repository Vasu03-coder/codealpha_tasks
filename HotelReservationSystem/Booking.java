import java.time.LocalDate;

/**
 * Represents a single hotel booking.
 * Contains customer details, room details, stay period, pricing
 * (including 18% GST) and booking status.
 */
public class Booking {

    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_CANCELLED = "CANCELLED";
    public static final double GST_RATE = 0.18;

    public static final String TABLE_FORMAT = "%-10s | %-22s | %-5s | %-9s | %-25s | %-11s | %-9s";

    private final String bookingId;
    private final Customer customer;
    private final int roomNumber;
    private final String roomType;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final long numberOfDays;
    private final double roomPrice;
    private final double baseAmount;
    private final double gstAmount;
    private final double totalAmount;
    private String status;

    /**
     * Constructor used when a new booking is created.
     * Number of days and all amounts are calculated automatically.
     */
    public Booking(String bookingId, Customer customer, int roomNumber, String roomType,
                   LocalDate checkIn, LocalDate checkOut, double roomPrice) {
        this(bookingId, customer, roomNumber, roomType, checkIn, checkOut,
                Utils.daysBetween(checkIn, checkOut), roomPrice, STATUS_ACTIVE);
    }

    /**
     * Full constructor used when restoring bookings from file.
     */
    public Booking(String bookingId, Customer customer, int roomNumber, String roomType,
                   LocalDate checkIn, LocalDate checkOut, long numberOfDays,
                   double roomPrice, String status) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfDays = numberOfDays;
        this.roomPrice = roomPrice;
        this.baseAmount = roomPrice * numberOfDays;
        this.gstAmount = baseAmount * GST_RATE;
        this.totalAmount = baseAmount + gstAmount;
        this.status = status;
    }

    /* ---------------------- Status helpers ---------------------- */

    public boolean isActive() {
        return STATUS_ACTIVE.equals(status);
    }

    public boolean isCancelled() {
        return STATUS_CANCELLED.equals(status);
    }

    public void cancel() {
        this.status = STATUS_CANCELLED;
    }

    /* ---------------------- Getters ---------------------- */

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public double getGstAmount() {
        return gstAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getBookingPeriod() {
        return checkIn + " to " + checkOut;
    }

    /* ---------------------- Display helpers ---------------------- */

    public static void printTableHeader() {
        System.out.println(String.format(TABLE_FORMAT, "Booking ID", "Customer Name", "Room",
                "Type", "Check-In to Check-Out", "Bill", "Status"));
        System.out.println(Utils.THIN_LINE);
    }

    public String getSummaryLine() {
        return String.format(TABLE_FORMAT, bookingId, truncate(customer.getName(), 22),
                roomNumber, roomType, getBookingPeriod(),
                Utils.formatCurrency(totalAmount), status);
    }

    public void printDetails() {
        Utils.printHeader("BOOKING DETAILS");
        System.out.printf("%-16s : %s%n", "Booking ID", bookingId);
        System.out.printf("%-16s : %s%n", "Status", status);
        Utils.printThinLine();
        System.out.printf("%-16s : %s%n", "Customer Name", customer.getName());
        System.out.printf("%-16s : %s%n", "Age", customer.getAge());
        System.out.printf("%-16s : %s%n", "Gender", customer.getGender());
        System.out.printf("%-16s : %s%n", "Phone", customer.getPhone());
        System.out.printf("%-16s : %s%n", "Email", customer.getEmail());
        System.out.printf("%-16s : %s%n", "Address", customer.getAddress());
        Utils.printThinLine();
        System.out.printf("%-16s : %s%n", "Room Number", roomNumber);
        System.out.printf("%-16s : %s%n", "Room Type", roomType);
        System.out.printf("%-16s : %s%n", "Check-In Date", checkIn);
        System.out.printf("%-16s : %s%n", "Check-Out Date", checkOut);
        System.out.printf("%-16s : %s%n", "Number of Days", numberOfDays);
        System.out.printf("%-16s : %s%n", "Price Per Night", Utils.formatCurrency(roomPrice));
        System.out.printf("%-16s : %s%n", "Base Amount", Utils.formatCurrency(baseAmount));
        System.out.printf("%-16s : %s%n", "GST (" + (int) (GST_RATE * 100) + "%)", Utils.formatCurrency(gstAmount));
        System.out.printf("%-16s : %s%n", "Total Amount", Utils.formatCurrency(totalAmount));
        Utils.printLine();
    }

    private static String truncate(String text, int maxLength) {
        if (text == null || text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }
}
