package flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest{
    private FlightService service;
    
    @BeforeEach
    void setUp(){
        service=new FlightService();
    }

    @Test
    void testSearchFlightsReturnsMatches(){
        Flight f1=new Flight("A1", "Paris", LocalDateTime.now(), 5);
        Flight f2=new Flight("B1", "London", LocalDateTime.now(), 5);
        service.addFlight(f1);
        service.addFlight(f2);

        List<Flight> results=service.searchFlights("Paris",LocalDateTime.now());

        assertEquals(1, results.size(), "Should return exactly 1 flight for Paris");
        assertEquals("A1", results.get(0).getFlightNumber(), "Should find flight A1");
    }

    @Test
    void testBookFlightDecreasesAvailability(){
        Flight f=new Flight("A1", "Paris", LocalDateTime.now(),10);
        service.bookFlight("Shiva", f, 3);
        assertEquals(7, f.getAvailableSeats(), "Available seats should decrease by booked amount");
    }

    @Test
    void testBookFlightPreventsOverbooking() {
        Flight f=new Flight("A1", "Paris", LocalDateTime.now(), 2);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.bookFlight("Shiva", f, 3);
        });
        assertTrue(exception.getMessage().contains("Not enough seats"), "Exception message should explain the error");
    }

    @Test
    void testBookingZeroSeatsThrowsError() {
        Flight f = new Flight("A1", "Paris",LocalDateTime.now(),5);
        
        Exception exception=assertThrows(IllegalArgumentException.class, () -> {
            service.bookFlight("Shiva", f, 0);
        });
        assertEquals("Must book at least one seat.", exception.getMessage());
    }
}