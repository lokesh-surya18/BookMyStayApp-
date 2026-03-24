/**
 * ============================================================
 * CLASS - CancellationService
 * ============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class is responsible for handling
 * booking cancellations.
 *
 * It ensures that:
 * - Cancelled room IDs are tracked
 * - Inventory is restored correctly
 * - Invalid cancellations are prevented
 *
 * A stack is used to model rollback behavior.
 *
 * @version 10.0
 */

import java.util.*;

public class CancellationService {

    /** Stack that stores recently released room IDs. */
    private Stack<String> releasedRoomIds;

    /** Maps reservation ID to room type. */
    private Map<String, String> reservationRoomTypeMap;

    /** Initializes cancellation tracking structures. */
    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers a confirmed booking.
     *
     * @param reservationId confirmed reservation ID
     * @param roomType allocated room type
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    /**
     * Cancels a confirmed booking and restores inventory.
     *
     * @param reservationId reservation to cancel
     * @param inventory centralized room inventory
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        // Validate reservation exists
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation: Reservation not found");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Push to stack (rollback tracking)
        releasedRoomIds.push(reservationId);

        // Restore inventory
        int current = inventory.getRoomAvailability().get(roomType);
        inventory.updateAvailability(roomType, current + 1);

        // Remove mapping (prevent duplicate cancel)
        reservationRoomTypeMap.remove(reservationId);

        System.out.println(
                "Booking cancelled successfully. Inventory restored for room type: "
                        + roomType
        );
    }

    /**
     * Displays recently cancelled reservations.
     */
    public void showRollbackHistory() {

        System.out.println();
        System.out.println("Rollback History (Most Recent First):");

        for (String id : releasedRoomIds) {
            System.out.println("Released Reservation ID: " + id);
        }
    }
}