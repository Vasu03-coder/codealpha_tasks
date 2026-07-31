import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles saving and loading bookings from the text file "bookings.txt".
 * Each booking is stored on a single line with fields separated by ";;".
 */
public class FileManager {

    private static final String FIELD_SEP = ";;";
    private static final String COMMENT_PREFIX = "#";
    private static final int EXPECTED_FIELDS = 15;

    /**
     * Saves all bookings to the given file.
     *
     * @throws IOException if the file cannot be written.
     */
    public static void saveBookings(ArrayList<Booking> bookings, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(COMMENT_PREFIX + " GRAND PALACE HOTEL - BOOKING DATA");
            writer.newLine();
            writer.write(COMMENT_PREFIX + " Format: bookingId;;name;;age;;gender;;phone;;email;;address;;roomNumber;;roomType;;checkIn;;checkOut;;days;;price;;total;;status");
            writer.newLine();
            for (Booking booking : bookings) {
                writer.write(toFileLine(booking));
                writer.newLine();
            }
        }
    }

    /**
     * Loads bookings from the given file.
     * Rooms occupied by active bookings are marked as unavailable.
     * Returns an empty map if the file does not exist.
     */
    public static HashMap<String, Booking> loadBookings(String filePath, Hotel hotel) {
        HashMap<String, Booking> bookings = new HashMap<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return bookings;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith(COMMENT_PREFIX)) {
                    continue;
                }
                try {
                    Booking booking = parseLine(line);
                    bookings.put(booking.getBookingId(), booking);
                    if (booking.isActive()) {
                        Room room = hotel.findRoom(booking.getRoomNumber());
                        if (room != null) {
                            room.setAvailable(false);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Skipping invalid booking record: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load bookings: " + e.getMessage());
        }
        return bookings;
    }

    private static String toFileLine(Booking booking) {
        Customer customer = booking.getCustomer();
        return String.join(FIELD_SEP,
                booking.getBookingId(),
                customer.getName(),
                String.valueOf(customer.getAge()),
                customer.getGender(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                String.valueOf(booking.getRoomNumber()),
                booking.getRoomType(),
                booking.getCheckIn().toString(),
                booking.getCheckOut().toString(),
                String.valueOf(booking.getNumberOfDays()),
                String.valueOf(booking.getRoomPrice()),
                String.valueOf(booking.getTotalAmount()),
                booking.getStatus());
    }

    private static Booking parseLine(String line) {
        String[] f = line.split(java.util.regex.Pattern.quote(FIELD_SEP));
        if (f.length != EXPECTED_FIELDS) {
            throw new IllegalArgumentException("Malformed record with " + f.length + " fields");
        }
        Customer customer = new Customer(f[1], Integer.parseInt(f[2]), f[3], f[4], f[5], f[6]);
        return new Booking(f[0], customer,
                Integer.parseInt(f[7]), f[8],
                LocalDate.parse(f[9]), LocalDate.parse(f[10]),
                Long.parseLong(f[11]), Double.parseDouble(f[12]), f[14]);
    }
}
