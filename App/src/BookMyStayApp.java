/**
 * ============================================================
 * MAIN CLASS - UseCase7AddOnServiceSelection
 * ============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class demonstrates how optional
 * services can be attached to a confirmed
 * booking.
 *
 * Services are added after room allocation
 * and do not affect inventory.
 *
 * @version 7.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        // Example reservation ID (from previous use case)
        String reservationId = "Single-1";

        // Initialize service manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500.0);
        AddOnService spa = new AddOnService("Spa", 1000.0);

        // Attach services
        serviceManager.addService(reservationId, breakfast);
        serviceManager.addService(reservationId, spa);

        // Calculate total cost
        double totalCost = serviceManager.calculateTotalServiceCost(reservationId);

        // Output
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}