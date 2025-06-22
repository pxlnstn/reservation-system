package strategy;

import manager.ReservationManager;
import model.Trip;
import model.User;

public class CancelReservationStrategy implements ReservationStrategy {

    private ReservationManager reservationManager;

    public CancelReservationStrategy(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    @Override
    public boolean execute(User user, Trip trip, String seatNumber) {
        return reservationManager.cancelReservation(user, trip, seatNumber);
    }
}
