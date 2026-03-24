/**
 * ============================================================
 * MAIN CLASS - UseCase12DataPersistenceRecovery
 * ============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * Demonstrates how system state can be
 * restored after an application restart.
 *
 * @version 12.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        String filePath = "inventory.txt";

        // Step 1: Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Step 2: Persistence service
        FilePersistenceService persistenceService = new FilePersistenceService();

        // Step 3: Load existing data (if any)
        persistenceService.loadInventory(inventory, filePath);

        // Step 4: Display current inventory
        System.out.println();
        System.out.println("Current Inventory:");

        System.out.println("Single: " +
                inventory.getRoomAvailability().get("Single"));
        System.out.println("Double: " +
                inventory.getRoomAvailability().get("Double"));
        System.out.println("Suite: " +
                inventory.getRoomAvailability().get("Suite"));

        // Step 5: Save inventory state
        persistenceService.saveInventory(inventory, filePath);
    }
}