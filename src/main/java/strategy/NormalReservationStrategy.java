package strategy;

import manager.ReservationManager;
import model.Trip;
import model.User;

public class NormalReservationStrategy implements ReservationStrategy {

    private ReservationManager reservationManager;

    public NormalReservationStrategy(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    @Override
    public boolean execute(User user, Trip trip, String seatNumber) {
        return reservationManager.makeReservation(user, trip, seatNumber);
    }
}
