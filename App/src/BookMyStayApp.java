/**
 * ============================================================
 * MAIN CLASS - UseCase2RoomInitialization
 * ============================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This class demonstrates room initialization
 * using domain models before introducing
 * centralized inventory management.
 *
 * Availability is represented using
 * simple variables to highlight limitations.
 *
 * @version 2.1
 */
public class BookMyStayApp {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        System.out.println("Hotel Room Initialization");
        System.out.println();

        System.out.println("Single Room:");
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailability);
        System.out.println();

        System.out.println("Double Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailability);
        System.out.println();

        System.out.println("Suite Room:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailability);
    }
}