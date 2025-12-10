package flight;

import java.time.LocalDateTime;

public class Flight {
    private String flightNumber;
    private String destination;
    private LocalDateTime departureTime;
    private int availableSeats;
    public Flight(String flightNumber, String destination, LocalDateTime departureTime, int availableSeats){
        this.flightNumber=flightNumber;
        this.destination=destination;
        this.departureTime=departureTime;
        this.availableSeats=availableSeats;
    }
    public String getFlightNumber(){
        return flightNumber;
    }
    public String getDestination(){
        return destination;
    }
    public LocalDateTime getDepartureTime(){
        return departureTime;
    }
    public int getAvailableSeats(){
        return availableSeats;
    }
    public void decreaseSeats(int seats)
    {
        if(seats>this.availableSeats)
        {
            throw new IllegalArgumentException("Not enough seats available.");
        }
        this.availableSeats -= seats;
    }
    @Override
    public String toString() {
        return "Flight[" + flightNumber + " to " + destination + ", Time: " + departureTime + ", Seats: " + availableSeats + "]";
    }
}