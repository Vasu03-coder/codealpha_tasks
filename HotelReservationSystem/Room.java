/**
 * Abstract base class representing a hotel room.
 * Concrete room categories (Standard, Deluxe, Suite) extend this class
 * and provide their own price per night through polymorphism.
 */
public abstract class Room {

    public static final String TYPE_STANDARD = "Standard";
    public static final String TYPE_DELUXE = "Deluxe";
    public static final String TYPE_SUITE = "Suite";

    private final int roomNumber;
    private final String roomType;
    private boolean available;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.available = true;
    }

    public abstract double getPricePerNight();

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - "
                + Utils.formatCurrency(getPricePerNight()) + "/night - "
                + (available ? "Available" : "Booked");
    }
}

/**
 * Standard room category. Price: Rs 1500 per night.
 */
class StandardRoom extends Room {

    public static final double PRICE = 1500.0;

    public StandardRoom(int roomNumber) {
        super(roomNumber, TYPE_STANDARD);
    }

    @Override
    public double getPricePerNight() {
        return PRICE;
    }
}

/**
 * Deluxe room category. Price: Rs 2500 per night.
 */
class DeluxeRoom extends Room {

    public static final double PRICE = 2500.0;

    public DeluxeRoom(int roomNumber) {
        super(roomNumber, TYPE_DELUXE);
    }

    @Override
    public double getPricePerNight() {
        return PRICE;
    }
}

/**
 * Suite room category. Price: Rs 4000 per night.
 */
class SuiteRoom extends Room {

    public static final double PRICE = 4000.0;

    public SuiteRoom(int roomNumber) {
        super(roomNumber, TYPE_SUITE);
    }

    @Override
    public double getPricePerNight() {
        return PRICE;
    }
}
