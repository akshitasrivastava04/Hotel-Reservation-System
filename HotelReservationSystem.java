
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Room class
class Room {
    private int roomNumber;
    private String category;
    private double payment;
    private boolean isAvailable;
    

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

// Reservation class
class Reservation {
    private int reservationId;
    private Room room;
    private String customerName;

    public Reservation(int reservationId, Room room, String customerName) {
        this.reservationId = reservationId;
        this.room = room;
        this.customerName = customerName;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", room=" + room +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}

// Payment class
class Payment {
    private int paymentId;
    private double amount;
    private String customerName;

    public Payment(int paymentId, double amount, String customerName) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}

// HotelReservationSystem class
public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    private static int reservationCounter = 1;
    private static int paymentCounter = 1;

    public static void main(String[] args) {
        initializeRooms();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Hotel Reservation System");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Process payment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchRooms(scanner);
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    processPayment(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 100.00));
        rooms.add(new Room(102, "Double", 150.00));
        rooms.add(new Room(103, "Suite", 200.00));
        rooms.add(new Room(104, "Single", 100.00));
        rooms.add(new Room(105, "Double", 150.00));
    }

    private static void searchRooms(Scanner scanner) {
        System.out.println("Search for available rooms");
        System.out.print("Enter room category (Single/Double/Suite): ");
        String category = scanner.next();

        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("Make a reservation");
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter customer name: ");
        String customerName = scanner.next();

        Room room = findRoomByNumber(roomNumber);
        if (room != null && room.isAvailable()) {
            room.setAvailable(false);
            Reservation reservation = new Reservation(reservationCounter++, room, customerName);
            reservations.add(reservation);
            System.out.println("Reservation successful. Reservation ID: " + reservation.getReservationId());
        } else {
            System.out.println("Room is not available.");
        }
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.println("View booking details");
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();

        Reservation reservation = findReservationById(reservationId);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static void processPayment(Scanner scanner) {
        System.out.println("Process payment");
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        
        Reservation reservation = findReservationById(reservationId);
        if (reservation != null) {
            Room room = reservation.getRoom();
            Payment payment = new Payment(paymentCounter++, room.getPrice(), reservation.getCustomerName());
            payments.add(payment);
            System.out.println("Payment processed successfully. Payment ID: " + payment.getPaymentId());
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private static Reservation findReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }
}
