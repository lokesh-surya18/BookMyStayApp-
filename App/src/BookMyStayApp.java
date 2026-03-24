/**
 * ============================================================
 * MAIN CLASS - UseCase6RoomAllocation
 * ============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class demonstrates how booking
 * requests are confirmed and rooms
 * are allocated safely.
 *
 * It consumes booking requests in FIFO
 * order and updates inventory immediately.
 *
 * @version 6.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        // Step 1: Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Step 2: Initialize queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Step 3: Add booking requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Step 4: Allocation service
        RoomAllocationService allocationService = new RoomAllocationService();

        // Step 5: Process queue (FIFO)
        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            allocationService.allocateRoom(request, inventory);
        }
    }
}