import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Manages all booking operations: creating, cancelling, searching,
 * statistics, saving and loading bookings.
 * Uses HashMap (for fast lookup by Booking ID) and ArrayList (ordered records).
 */
public class BookingManager {

    private final Hotel hotel;
    private final HashMap<String, Booking> bookings;
    private final ArrayList<Booking> bookingList;
    private final Random random;

    public BookingManager(Hotel hotel) {
        this.hotel = hotel;
        this.bookings = new HashMap<>();
        this.bookingList = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Books the first available room of the requested type.
     *
     * @throws IllegalStateException if no room of that type is available.
     */
    public Booking bookRoom(Customer customer, String roomType,
                            LocalDate checkIn, LocalDate checkOut) {
        Room room = hotel.findAvailableRoomByType(roomType);
        String bookingId = generateBookingId();
        Booking booking = new Booking(bookingId, customer, room.getRoomNumber(),
                room.getRoomType(), checkIn, checkOut, room.getPricePerNight());
        bookings.put(bookingId, booking);
        bookingList.add(booking);
        room.setAvailable(false);
        return booking;
    }

    /**
     * Generates a unique booking ID such as BK483920.
     * Uniqueness is guaranteed against all existing bookings.
     */
    private String generateBookingId() {
        String bookingId;
        do {
            bookingId = "BK" + (100000 + random.nextInt(900000));
        } while (bookings.containsKey(bookingId));
        return bookingId;
    }

    /**
     * Cancels an active booking and makes the room available again.
     *
     * @throws IllegalArgumentException if the booking ID is not found.
     * @throws IllegalStateException    if the booking is already cancelled.
     */
    public Booking cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking ID not found: " + bookingId);
        }
        if (booking.isCancelled()) {
            throw new IllegalStateException("This booking is already cancelled.");
        }
        Room room = hotel.findRoom(booking.getRoomNumber());
        if (room != null) {
            room.setAvailable(true);
        }
        booking.cancel();
        return booking;
    }

    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }

    public ArrayList<Booking> getAllBookings() {
        return bookingList;
    }

    public ArrayList<Booking> searchByCustomerName(String name) {
        ArrayList<Booking> results = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getCustomer().getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(booking);
            }
        }
        return results;
    }

    public ArrayList<Booking> searchByPhone(String phone) {
        ArrayList<Booking> results = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getCustomer().getPhone().equals(phone)) {
                results.add(booking);
            }
        }
        return results;
    }

    public ArrayList<Booking> getTodaysBookings() {
        ArrayList<Booking> todays = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Booking booking : bookingList) {
            if (booking.getCheckIn().equals(today)) {
                todays.add(booking);
            }
        }
        return todays;
    }

    public Statistics getStatistics() {
        int cancelledBookings = 0;
        double totalRevenue = 0.0;
        for (Booking booking : bookingList) {
            if (booking.isCancelled()) {
                cancelledBookings++;
            } else {
                totalRevenue += booking.getTotalAmount();
            }
        }
        return new Statistics(
                hotel.getTotalRooms(),
                hotel.getBookedRoomCount(),
                hotel.getAvailableRoomCount(),
                bookingList.size(),
                cancelledBookings,
                totalRevenue,
                hotel.getOccupancyPercentage(),
                getTodaysBookings().size());
    }

    /* ---------------------- File persistence ---------------------- */

    public void saveToFile(String filePath) throws java.io.IOException {
        FileManager.saveBookings(bookingList, filePath);
    }

    public void loadFromFile(String filePath) {
        HashMap<String, Booking> loaded = FileManager.loadBookings(filePath, hotel);
        bookings.clear();
        bookingList.clear();
        bookings.putAll(loaded);
        bookingList.addAll(loaded.values());
        System.out.println("Loaded " + bookingList.size() + " booking(s) from " + filePath);
    }

    /**
     * Immutable snapshot of booking statistics.
     */
    public static class Statistics {

        private final int totalRooms;
        private final int bookedRooms;
        private final int availableRooms;
        private final int totalBookings;
        private final int cancelledBookings;
        private final double totalRevenue;
        private final double occupancyPercentage;
        private final int todaysBookings;

        public Statistics(int totalRooms, int bookedRooms, int availableRooms,
                          int totalBookings, int cancelledBookings, double totalRevenue,
                          double occupancyPercentage, int todaysBookings) {
            this.totalRooms = totalRooms;
            this.bookedRooms = bookedRooms;
            this.availableRooms = availableRooms;
            this.totalBookings = totalBookings;
            this.cancelledBookings = cancelledBookings;
            this.totalRevenue = totalRevenue;
            this.occupancyPercentage = occupancyPercentage;
            this.todaysBookings = todaysBookings;
        }

        public int getTotalRooms() {
            return totalRooms;
        }

        public int getBookedRooms() {
            return bookedRooms;
        }

        public int getAvailableRooms() {
            return availableRooms;
        }

        public int getTotalBookings() {
            return totalBookings;
        }

        public int getCancelledBookings() {
            return cancelledBookings;
        }

        public double getTotalRevenue() {
            return totalRevenue;
        }

        public double getOccupancyPercentage() {
            return occupancyPercentage;
        }

        public int getTodaysBookings() {
            return todaysBookings;
        }
    }
}
