import java.util.HashMap;
import java.util.Map;

// Room class (Domain Model)
class Room {
    String type;
    int beds;
    double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    @Override
    public String toString() {
        return type + " | Beds: " + beds + " | Price: ₹" + price;
    }
}

// Inventory (same idea as UC3, no modification here)
class RoomInventory {
    private Map<String, Integer> availability = new HashMap<>();
    private Map<String, Room> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single Room", new Room("Single Room", 1, 1200));
        rooms.put("Double Room", new Room("Double Room", 2, 2200));
        rooms.put("Suite Room", new Room("Suite Room", 3, 5000));

        availability.put("Single Room", 5);
        availability.put("Double Room", 0); // unavailable
        availability.put("Suite Room", 2);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }
}

// Search Service (READ-ONLY)
class RoomSearchService {
    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms:");

        for (String type : inventory.getRooms().keySet()) {
            int count = inventory.getAvailability(type);

            if (count > 0) { // filter unavailable
                System.out.println(
                        inventory.getRooms().get(type) +
                                " | Available: " + count
                );
            }
        }
    }
}

// Main class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP         ");
        System.out.println("     Room Search & Availability  ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        // Read-only search
        RoomSearchService search = new RoomSearchService(inventory);
        search.displayAvailableRooms();
    }
}