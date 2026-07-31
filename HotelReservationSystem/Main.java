import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Main entry point of the Grand Palace Hotel Reservation System.
 * Provides the console menu and ties together all other classes.
 */
public class Main {

    private static final String BOOKINGS_FILE = "bookings.txt";

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        BookingManager bookingManager = new BookingManager(hotel);
        bookingManager.loadFromFile(BOOKINGS_FILE);

        showWelcomeScreen();

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = Utils.readInt("Choose Option: ");
            switch (choice) {
                case 1:
                    viewAvailableRooms(hotel);
                    break;
                case 2:
                    bookRoom(hotel, bookingManager);
                    break;
                case 3:
                    cancelBooking(bookingManager);
                    break;
                case 4:
                    viewBookingDetails(bookingManager);
                    break;
                case 5:
                    viewAllBookings(bookingManager);
                    break;
                case 6:
                    searchBooking(bookingManager);
                    break;
                case 7:
                    generateBill(bookingManager);
                    break;
                case 8:
                    viewStatistics(bookingManager);
                    break;
                case 9:
                    saveBookings(bookingManager);
                    break;
                case 10:
                    saveBookings(bookingManager);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 10.");
            }
        }
        showExitScreen();
    }

    /* ---------------------- Screens ---------------------- */

    private static void showWelcomeScreen() {
        Utils.printLine();
        Utils.printCentered(Hotel.HOTEL_NAME);
        Utils.printCentered("Hotel Reservation System");
        Utils.printCentered("CodeAlpha - Java Programming Internship (Task 4)");
        Utils.printLine();
        Utils.printCentered("Welcome!");
        Utils.printLine();
    }

    private static void showMainMenu() {
        Utils.printLine();
        Utils.printCentered(Hotel.HOTEL_NAME);
        Utils.printCentered("Hotel Reservation System");
        Utils.printLine();
        System.out.println("1. View Available Rooms");
        System.out.println("2. Book Room");
        System.out.println("3. Cancel Booking");
        System.out.println("4. View Booking Details");
        System.out.println("5. View All Bookings");
        System.out.println("6. Search Booking");
        System.out.println("7. Generate Bill");
        System.out.println("8. Booking Statistics");
        System.out.println("9. Save Bookings");
        System.out.println("10. Exit");
        Utils.printThinLine();
    }

    private static void showExitScreen() {
        Utils.printLine();
        Utils.printCentered("THANK YOU!");
        Utils.printCentered("Visit Again!");
        Utils.printCentered("Goodbye from " + Hotel.HOTEL_NAME);
        Utils.printLine();
    }

    /* ---------------------- Feature 1 ---------------------- */

    private static void viewAvailableRooms(Hotel hotel) {
        Utils.printHeader("ROOM LIST & AVAILABILITY");
        System.out.printf("%-9s | %-10s | %-13s | %-10s%n", "Room No", "Type", "Price/Night", "Status");
        System.out.println(Utils.THIN_LINE);
        for (Room room : hotel.getRooms()) {
            System.out.printf("%-9d | %-10s | %-13s | %-10s%n",
                    room.getRoomNumber(),
                    room.getRoomType(),
                    Utils.formatCurrency(room.getPricePerNight()),
                    room.isAvailable() ? "Available" : "Booked");
        }
        Utils.printLine();
        System.out.println("Available: " + hotel.getAvailableRoomCount()
                + " | Booked: " + hotel.getBookedRoomCount()
                + " | Total: " + hotel.getTotalRooms());
        Utils.printLine();
    }

    /* ---------------------- Feature 2 ---------------------- */

    private static void bookRoom(Hotel hotel, BookingManager bookingManager) {
        Utils.printHeader("BOOK A ROOM");

        String name = Utils.readLine("Enter Customer Name        : ");
        int age;
        while (true) {
            age = Utils.readInt("Enter Customer Age         : ");
            if (Utils.isValidAge(age)) {
                break;
            }
            System.out.println("Age must be between 1 and 120.");
        }
        String gender = readGender();
        String phone = readPhone();
        String email = readEmail();
        String address = Utils.readLine("Enter Address              : ");

        Customer customer = new Customer(name, age, gender, phone, email, address);

        System.out.println();
        System.out.println("Room Categories:");
        System.out.println("1. Standard - " + Utils.formatCurrency(1500.0) + "/night"
                + " (" + hotel.getAvailableCountByType(Room.TYPE_STANDARD) + " available)");
        System.out.println("2. Deluxe   - " + Utils.formatCurrency(2500.0) + "/night"
                + " (" + hotel.getAvailableCountByType(Room.TYPE_DELUXE) + " available)");
        System.out.println("3. Suite    - " + Utils.formatCurrency(4000.0) + "/night"
                + " (" + hotel.getAvailableCountByType(Room.TYPE_SUITE) + " available)");

        String roomType = null;
        while (roomType == null) {
            int roomChoice = Utils.readInt("Select Room Type (1-3)     : ");
            switch (roomChoice) {
                case 1:
                    roomType = Room.TYPE_STANDARD;
                    break;
                case 2:
                    roomType = Room.TYPE_DELUXE;
                    break;
                case 3:
                    roomType = Room.TYPE_SUITE;
                    break;
                default:
                    System.out.println("Invalid choice. Select 1, 2 or 3.");
            }
        }

        LocalDate checkIn;
        while (true) {
            checkIn = Utils.readDate("Enter Check-In Date (yyyy-MM-dd)  : ");
            if (!checkIn.isBefore(LocalDate.now())) {
                break;
            }
            System.out.println("Check-In date cannot be in the past.");
        }
        LocalDate checkOut;
        while (true) {
            checkOut = Utils.readDate("Enter Check-Out Date (yyyy-MM-dd) : ");
            if (checkOut.isAfter(checkIn)) {
                break;
            }
            System.out.println("Check-Out date must be after Check-In date.");
        }

        try {
            Booking booking = bookingManager.bookRoom(customer, roomType, checkIn, checkOut);
            Utils.printLine();
            Utils.printCentered("BOOKING SUCCESSFUL");
            Utils.printLine();
            System.out.println("Booking ID      : " + booking.getBookingId());
            System.out.println("Customer        : " + customer.getName());
            System.out.println("Room Assigned   : " + booking.getRoomNumber()
                    + " (" + booking.getRoomType() + ")");
            System.out.println("Check-In        : " + booking.getCheckIn());
            System.out.println("Check-Out       : " + booking.getCheckOut());
            System.out.println("Number of Days  : " + booking.getNumberOfDays());
            System.out.println("Total Bill      : " + Utils.formatCurrency(booking.getTotalAmount()));
            System.out.println("Status          : " + booking.getStatus());
            Utils.printLine();
        } catch (IllegalStateException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    private static String readGender() {
        while (true) {
            String gender = Utils.readLine("Enter Gender (M/F/Other)   : ").toUpperCase();
            switch (gender) {
                case "M":
                    return "Male";
                case "F":
                    return "Female";
                case "OTHER":
                    return "Other";
                default:
                    System.out.println("Invalid gender. Use M, F or Other.");
            }
        }
    }

    private static String readPhone() {
        while (true) {
            String phone = Utils.readLine("Enter Phone Number        : ");
            if (Utils.isValidPhone(phone)) {
                return phone;
            }
            System.out.println("Phone number must contain exactly 10 digits.");
        }
    }

    private static String readEmail() {
        while (true) {
            String email = Utils.readLine("Enter Email Address       : ");
            if (Utils.isValidEmail(email)) {
                return email;
            }
            System.out.println("Invalid email address. It must contain '@'.");
        }
    }

    /* ---------------------- Feature 3 ---------------------- */

    private static void cancelBooking(BookingManager bookingManager) {
        Utils.printHeader("CANCEL BOOKING");
        String bookingId = Utils.readLine("Enter Booking ID     : ");
        try {
            Booking booking = bookingManager.cancelBooking(bookingId);
            Utils.printLine();
            Utils.printCentered("BOOKING CANCELLED");
            Utils.printLine();
            System.out.println("Booking ID   : " + booking.getBookingId());
            System.out.println("Customer     : " + booking.getCustomer().getName());
            System.out.println("Room         : " + booking.getRoomNumber());
            System.out.println("Room " + booking.getRoomNumber()
                    + " is now available again.");
            Utils.printLine();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Cancellation failed: " + e.getMessage());
        }
    }

    /* ---------------------- Feature 4 ---------------------- */

    private static void viewBookingDetails(BookingManager bookingManager) {
        Utils.printHeader("VIEW BOOKING DETAILS");
        String bookingId = Utils.readLine("Enter Booking ID : ");
        Booking booking = bookingManager.getBooking(bookingId);
        if (booking == null) {
            System.out.println("No booking found with ID: " + bookingId);
            Utils.printLine();
            return;
        }
        booking.printDetails();
    }

    /* ---------------------- Feature 5 ---------------------- */

    private static void viewAllBookings(BookingManager bookingManager) {
        printBookingsTable(bookingManager.getAllBookings(), "ALL BOOKINGS");
    }

    private static void printBookingsTable(ArrayList<Booking> bookings, String title) {
        Utils.printHeader(title);
        Booking.printTableHeader();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking.getSummaryLine());
            }
        }
        Utils.printLine();
    }

    /* ---------------------- Feature 6 ---------------------- */

    private static void searchBooking(BookingManager bookingManager) {
        Utils.printHeader("SEARCH BOOKING");
        System.out.println("Search by:");
        System.out.println("1. Booking ID");
        System.out.println("2. Customer Name");
        System.out.println("3. Phone Number");
        Utils.printThinLine();

        int option = Utils.readInt("Select Search Option (1-3): ");
        switch (option) {
            case 1: {
                String id = Utils.readLine("Enter Booking ID: ");
                Booking booking = bookingManager.getBooking(id);
                if (booking == null) {
                    System.out.println("No booking found with ID: " + id);
                    Utils.printLine();
                } else {
                    ArrayList<Booking> result = new ArrayList<>();
                    result.add(booking);
                    printBookingsTable(result, "SEARCH RESULT");
                }
                break;
            }
            case 2: {
                String name = Utils.readLine("Enter Customer Name: ");
                printBookingsTable(bookingManager.searchByCustomerName(name), "SEARCH RESULT");
                break;
            }
            case 3: {
                String phone = readPhone();
                printBookingsTable(bookingManager.searchByPhone(phone), "SEARCH RESULT");
                break;
            }
            default:
                System.out.println("Invalid option.");
        }
    }

    /* ---------------------- Feature 7 ---------------------- */

    private static void generateBill(BookingManager bookingManager) {
        Utils.printHeader("GENERATE BILL");
        String bookingId = Utils.readLine("Enter Booking ID : ");
        Booking booking = bookingManager.getBooking(bookingId);
        if (booking == null) {
            System.out.println("No booking found with ID: " + bookingId);
            Utils.printLine();
            return;
        }
        BillGenerator.generateBill(booking);
    }

    /* ---------------------- Feature 8 ---------------------- */

    private static void viewStatistics(BookingManager bookingManager) {
        BookingManager.Statistics stats = bookingManager.getStatistics();
        Utils.printHeader("BOOKING STATISTICS");
        System.out.println("Total Rooms          : " + stats.getTotalRooms());
        System.out.println("Booked Rooms         : " + stats.getBookedRooms());
        System.out.println("Available Rooms      : " + stats.getAvailableRooms());
        System.out.printf("Room Occupancy       : %.1f%%%n", stats.getOccupancyPercentage());
        System.out.println("Total Bookings       : " + stats.getTotalBookings());
        System.out.println("Cancelled Bookings   : " + stats.getCancelledBookings());
        System.out.println("Total Revenue        : " + Utils.formatCurrency(stats.getTotalRevenue()));
        Utils.printThinLine();

        ArrayList<Booking> todays = bookingManager.getTodaysBookings();
        System.out.println("Today's Bookings     : " + stats.getTodaysBookings());
        for (Booking booking : todays) {
            System.out.println("  - " + booking.getBookingId()
                    + " | " + booking.getCustomer().getName()
                    + " | Room " + booking.getRoomNumber()
                    + " | " + booking.getStatus());
        }
        Utils.printLine();
    }

    /* ---------------------- Feature 9 ---------------------- */

    private static void saveBookings(BookingManager bookingManager) {
        try {
            bookingManager.saveToFile(BOOKINGS_FILE);
            System.out.println("Bookings saved successfully to " + BOOKINGS_FILE);
        } catch (java.io.IOException e) {
            System.out.println("Failed to save bookings: " + e.getMessage());
        }
    }
}
