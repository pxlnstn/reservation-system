package strategy;

import model.User;
import model.Trip;

public interface ReservationStrategy {
    boolean execute(User user, Trip trip, String seatNumber);
}
