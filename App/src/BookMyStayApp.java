/**
 * ============================================================
 * MAIN CLASS - UseCase8BookingHistoryReport
 * ============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class demonstrates how
 * confirmed bookings are stored
 * and reported.
 *
 * The system maintains an ordered
 * audit trail of reservations.
 *
 * @version 8.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting");

        // Step 1: Create booking history
        BookingHistory history = new BookingHistory();

        // Step 2: Add confirmed reservations
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // Step 3: Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}