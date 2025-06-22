package strategy;

import model.Trip;
import model.User;

public class StrategyContext {

    private ReservationStrategy strategy;

    public void setStrategy(ReservationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean apply(User user, Trip trip, String seatNumber) {
        return strategy.execute(user, trip, seatNumber);
    }
}
