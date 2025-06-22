package manager;

import model.Reservation;
import model.Seat;
import model.Trip;
import model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private List<Reservation> reservations;

    public ReservationManager() {
        this.reservations = new ArrayList<>();
    }

    public boolean makeReservation(User user, Trip trip, String seatNumber) {
        Seat seat = trip.getSeatByNumber(seatNumber);
        if (seat == null || seat.isReserved()) {
            return false;
        }

        seat.reserve(user);
        Reservation r = new Reservation(user, trip, seat, LocalDateTime.now());
        reservations.add(r);
        return true;
    }

    public boolean cancelReservation(User user, Trip trip, String seatNumber) {
        Reservation found = null;

        for (Reservation r : reservations) {
            if (r.getUser().equals(user) &&
                    r.getTrip().equals(trip) &&
                    r.getSeat().getSeatNumber().equals(seatNumber)) {
                found = r;
                break;
            }
        }

        if (found != null) {
            found.getSeat().cancel();
            reservations.remove(found);
            return true;
        }

        return false;
    }

    public List<Reservation> getReservationsByUser(User user) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getUser().equals(user)) {
                result.add(r);
            }
        }
        return result;
    }
}

