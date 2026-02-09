import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    boolean isBooked;

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }
}

class Reservation {
    int roomNumber;
    String customerName;

    Reservation(int roomNumber, String customerName) {
        this.roomNumber = roomNumber;
        this.customerName = customerName;
    }
}

public class Main {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n🏨 Hotel Reservation System developed by Monty");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. View Reservations");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    viewRooms();
                    break;
                case 3:
                    bookRoom();
                    break;
                case 4:
                    viewReservations();
                    break;
                case 5:
                    cancelReservation();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 6);
    }

    static void addRoom() {
        System.out.print("Enter room number: ");
        int roomNo = scanner.nextInt();

        rooms.add(new Room(roomNo));
        System.out.println("Room added successfully.");
    }

    static void viewRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        for (Room r : rooms) {
            String status = r.isBooked ? "Booked" : "Available";
            System.out.println("Room: " + r.roomNumber + " - " + status);
        }
    }

    static void bookRoom() {
        System.out.print("Enter room number: ");
        int roomNo = scanner.nextInt();
        scanner.nextLine();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && !r.isBooked) {
                System.out.print("Enter customer name: ");
                String name = scanner.nextLine();

                r.isBooked = true;
                reservations.add(new Reservation(roomNo, name));
                System.out.println("Room booked successfully.");
                return;
            }
        }

        System.out.println("Room not available.");
    }

    static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        for (Reservation r : reservations) {
            System.out.println("Room: " + r.roomNumber + ", Customer: " + r.customerName);
        }
    }

    static void cancelReservation() {
        System.out.print("Enter room number to cancel: ");
        int roomNo = scanner.nextInt();

        for (Reservation res : reservations) {
            if (res.roomNumber == roomNo) {
                reservations.remove(res);
                for (Room r : rooms) {
                    if (r.roomNumber == roomNo) {
                        r.isBooked = false;
                        break;
                    }
                }
                System.out.println("Reservation cancelled.");
                return;
            }
        }

        System.out.println("Reservation not found.");
    }
}
