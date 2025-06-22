package model;

import java.time.LocalDateTime;

public class Reservation {
    private User user;
    private Trip trip;
    private Seat seat;
    private LocalDateTime reservationDate;

    public Reservation(User user, Trip trip, Seat seat, LocalDateTime reservationDate) {
        this.user = user;
        this.trip = trip;
        this.seat = seat;
        this.reservationDate = reservationDate;
    }

    public User getUser() { return user; }
    public Trip getTrip() { return trip; }
    public Seat getSeat() { return seat; }
    public LocalDateTime getReservationDate() { return reservationDate; }
}
