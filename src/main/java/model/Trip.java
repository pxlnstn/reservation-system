package model;

import java.time.LocalDateTime;
import java.util.List;

public class Trip {
    private String id;
    private String type; // "Bus" or "Flight"
    private String origin;
    private String destination;
    private LocalDateTime dateTime;
    private List<Seat> seats;

    public Trip(String id, String type, String origin, String destination, LocalDateTime dateTime, List<Seat> seats) {
        this.id = id;
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
        this.seats = seats;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public LocalDateTime getDateTime() { return dateTime; }
    public List<Seat> getSeats() { return seats; }

    public Seat getSeatByNumber(String number) {
        return seats.stream()
                .filter(seat -> seat.getSeatNumber().equals(number))
                .findFirst()
                .orElse(null);
    }
}

