package flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService{
    private List<Flight> flights=new ArrayList<>();
    private List<Reservation> reservations=new ArrayList<>();

    public void addFlight(Flight flight){
        flights.add(flight);
    }
    public List<Flight> searchFlights(String destination, LocalDateTime date){
        return flights.stream()
                .filter(f->f.getDestination().equalsIgnoreCase(destination))
                .filter(f->f.getDepartureTime().toLocalDate().equals(date.toLocalDate()))
                .filter(f->f.getAvailableSeats() > 0)
                .collect(Collectors.toList());
    }
    public Reservation bookFlight(String customerName, Flight flight, int seats) {
        if (seats <= 0) {
            throw new IllegalArgumentException("Must book at least one seat.");
        }
        synchronized (flight){
            if (flight.getAvailableSeats() < seats) {
                throw new IllegalStateException("Booking failed: Not enough seats available.");
            }
            flight.decreaseSeats(seats);
            Reservation res=new Reservation(customerName, flight, seats);
            reservations.add(res);
            System.out.println("Booking confirmed for " + customerName);
            return res;
        }
    }
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }
}