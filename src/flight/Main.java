package flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FlightService service = new FlightService();
        Scanner scanner = new Scanner(System.in);

        service.addFlight(new Flight("A101", "New York", LocalDateTime.now().plusDays(1), 10));
        service.addFlight(new Flight("B202", "London", LocalDateTime.now().plusDays(1), 2));

        while (true) {
            System.out.println("\n--- Flight Reservation System ---");
            System.out.println("1. Search Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter destination: ");
                    String dest = scanner.nextLine();
                    System.out.println("Searching for tomorrow's flights to " + dest + "...");
                    List<Flight> results = service.searchFlights(dest, LocalDateTime.now().plusDays(1));
                    results.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Enter Flight Number to book: ");
                    String fNum = scanner.nextLine();

                    Flight selectedFlight = null;
                    selectedFlight = service.searchFlights("New York", LocalDateTime.now().plusDays(1)).stream()
                            .filter(f -> f.getFlightNumber().equalsIgnoreCase(fNum))
                            .findFirst()
                            .orElse(null);

                    if (selectedFlight == null) {
                        selectedFlight = service.searchFlights("London", LocalDateTime.now().plusDays(1)).stream()
                                .filter(f -> f.getFlightNumber().equalsIgnoreCase(fNum))
                                .findFirst()
                                .orElse(null);
                    }

                    if (selectedFlight == null) {
                        System.out.println("Flight not found.");
                        break;
                    }

                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Seats to book: ");
                    int seats = scanner.nextInt();

                    try {
                        service.bookFlight(name, selectedFlight, seats);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    service.getAllReservations().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }

    }
}