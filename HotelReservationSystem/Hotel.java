import java.util.ArrayList;

/**
 * Represents the hotel and manages its collection of rooms.
 * Rooms are created automatically when the application starts:
 * 101-110 Standard, 201-210 Deluxe, 301-310 Suite.
 */
public class Hotel {

    public static final String HOTEL_NAME = "Grand Palace Hotel";
    public static final int TOTAL_ROOMS = 30;
    public static final int ROOMS_PER_CATEGORY = 10;

    private final ArrayList<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
        initializeRooms();
    }

    /**
     * Creates all 30 rooms automatically.
     */
    private void initializeRooms() {
        for (int i = 1; i <= ROOMS_PER_CATEGORY; i++) {
            rooms.add(new StandardRoom(100 + i));
            rooms.add(new DeluxeRoom(200 + i));
            rooms.add(new SuiteRoom(300 + i));
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public int getTotalRooms() {
        return rooms.size();
    }

    public Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    /**
     * Finds the first available room of the given category.
     *
     * @throws IllegalStateException if no room of that category is available.
     */
    public Room findAvailableRoomByType(String roomType) {
        for (Room room : rooms) {
            if (room.getRoomType().equals(roomType) && room.isAvailable()) {
                return room;
            }
        }
        throw new IllegalStateException("No available rooms in category: " + roomType);
    }

    public int getAvailableCountByType(String roomType) {
        int count = 0;
        for (Room room : rooms) {
            if (room.getRoomType().equals(roomType) && room.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public int getBookedRoomCount() {
        int count = 0;
        for (Room room : rooms) {
            if (!room.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public int getAvailableRoomCount() {
        return rooms.size() - getBookedRoomCount();
    }

    public double getOccupancyPercentage() {
        return (getBookedRoomCount() * 100.0) / rooms.size();
    }
}
