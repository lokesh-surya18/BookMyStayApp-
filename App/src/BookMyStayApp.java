/**
 * ============================================================
 * MAIN CLASS - UseCase10BookingCancellation
 * ============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class demonstrates how confirmed
 * bookings can be cancelled safely.
 *
 * Inventory is restored and rollback
 * history is maintained.
 *
 * @version 10.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        // Step 1: Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Step 2: Initialize cancellation service
        CancellationService cancellationService = new CancellationService();

        // Step 3: Simulate confirmed booking
        String reservationId = "Single-1";
        cancellationService.registerBooking(reservationId, "Single");

        // Reduce inventory (simulate allocation)
        int current = inventory.getRoomAvailability().get("Single");
        inventory.updateAvailability("Single", current - 1);

        // Step 4: Cancel booking
        cancellationService.cancelBooking(reservationId, inventory);

        // Step 5: Show rollback history
        cancellationService.showRollbackHistory();

        // Step 6: Display updated inventory
        System.out.println();
        System.out.println("Updated Single Room Availability: " +
                inventory.getRoomAvailability().get("Single"));
    }
}