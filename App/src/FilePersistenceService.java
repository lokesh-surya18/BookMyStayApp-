/**
 * ============================================================
 * CLASS - FilePersistenceService
 * ============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class is responsible for persisting
 * critical system state to a plain text file.
 *
 * It supports:
 * - Saving room inventory state
 * - Restoring inventory on system startup
 *
 * @version 12.0
 */

import java.io.*;
import java.util.Map;

public class FilePersistenceService {

    /**
     * Saves room inventory state to a file.
     *
     * Format:
     * roomType=availableCount
     */
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry :
                    inventory.getRoomAvailability().entrySet()) {

                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Loads room inventory state from a file.
     */
    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        // Handle missing file
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    inventory.updateAvailability(roomType, count);
                }
            }

            System.out.println("Inventory loaded successfully.");

        } catch (Exception e) {
            System.out.println("Error loading inventory. Starting fresh.");
        }
    }
}