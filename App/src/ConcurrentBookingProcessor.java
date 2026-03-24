/**
 * ============================================================
 * CLASS - ConcurrentBookingProcessor
 * ============================================================
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * This class represents a booking processor
 * that can be executed by multiple threads.
 *
 * It demonstrates how shared resources
 * must be accessed in a thread-safe manner.
 *
 * @version 11.0
 */
public class ConcurrentBookingProcessor implements Runnable {

    /** Shared booking request queue. */
    private BookingRequestQueue bookingQueue;

    /** Shared room inventory. */
    private RoomInventory inventory;

    /** Shared room allocation service. */
    private RoomAllocationService allocationService;

    /**
     * Creates a new booking processor.
     */
    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    /**
     * Executes booking processing logic.
     */
    @Override
    public void run() {

        while (true) {
            Reservation request;

            // 🔒 Synchronize queue access (critical section)
            synchronized (bookingQueue) {
                if (!bookingQueue.hasPendingRequests()) {
                    break;
                }
                request = bookingQueue.getNextRequest();
            }

            // 🔒 Synchronize allocation (critical section)
            synchronized (allocationService) {
                allocationService.allocateRoom(request, inventory);
            }
        }
    }
}