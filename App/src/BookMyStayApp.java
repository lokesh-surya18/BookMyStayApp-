/**
 * ============================================================
 * MAIN CLASS - UseCase11ConcurrentBookingSimulation
 * ============================================================
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * Simulates multiple users attempting to
 * book rooms at the same time.
 *
 * Demonstrates how synchronization prevents
 * inconsistent allocations.
 *
 * @version 11.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation");

        // Step 1: Shared resources
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Step 2: Add booking requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        // Step 3: Create threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        // Step 4: Start threads
        t1.start();
        t2.start();

        // Step 5: Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Step 6: Display remaining inventory
        System.out.println();
        System.out.println("Remaining Inventory:");

        System.out.println("Single: " +
                inventory.getRoomAvailability().get("Single"));
        System.out.println("Double: " +
                inventory.getRoomAvailability().get("Double"));
        System.out.println("Suite: " +
                inventory.getRoomAvailability().get("Suite"));
    }
}