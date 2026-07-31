/**
 * Generates a professional formatted invoice (bill) for a booking.
 * Includes room charges, 18% GST and the final amount.
 */
public class BillGenerator {

    public static final double GST_RATE = 0.18;

    /**
     * Generates a full invoice with the thank-you message (method overloading).
     */
    public static void generateBill(Booking booking) {
        generateBill(booking, true);
    }

    /**
     * Generates the invoice. If showThankYou is false, the closing message
     * is omitted (used by other features if needed).
     */
    public static void generateBill(Booking booking, boolean showThankYou) {
        Customer customer = booking.getCustomer();

        Utils.printHeader("HOTEL INVOICE");
        Utils.printCentered(Hotel.HOTEL_NAME);
        Utils.printCentered(Utils.THIN_LINE);
        System.out.printf("%-16s : %s%n", "Booking ID", booking.getBookingId());
        System.out.printf("%-16s : %s%n", "Customer Name", customer.getName());
        Utils.printThinLine();
        System.out.printf("%-16s : %s%n", "Room Number", booking.getRoomNumber());
        System.out.printf("%-16s : %s%n", "Room Type", booking.getRoomType());
        System.out.printf("%-16s : %s%n", "Check-In", booking.getCheckIn());
        System.out.printf("%-16s : %s%n", "Check-Out", booking.getCheckOut());
        System.out.printf("%-16s : %s%n", "Days", booking.getNumberOfDays());
        System.out.printf("%-16s : %s%n", "Price Per Night", Utils.formatCurrency(booking.getRoomPrice()));
        Utils.printThinLine();
        System.out.printf("%-16s : %s%n", "Room Charges", Utils.formatCurrency(booking.getBaseAmount()));
        System.out.printf("%-16s : %s%n", "GST (18%)", Utils.formatCurrency(booking.getGstAmount()));
        Utils.printThinLine();
        System.out.printf("%-16s : %s%n", "Final Amount", Utils.formatCurrency(booking.getTotalAmount()));
        Utils.printLine();

        if (showThankYou) {
            Utils.printCentered("Thank you for choosing " + Hotel.HOTEL_NAME + "!");
            Utils.printCentered("Visit Again!");
            Utils.printLine();
        }
    }
}
