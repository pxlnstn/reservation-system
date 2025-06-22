package manager;
import model.Trip;
import java.util.ArrayList;
import java.util.List;
public class TripManager {
    private static TripManager instance;
    private List<Trip> trips;
    private TripManager() {
        trips = new ArrayList<>();
    }
    public static TripManager getInstance() {
        if (instance == null) {
            instance = new TripManager();
        }
        return instance;
    }
    public void addTrip(Trip trip) {
        trips.add(trip);
    }
    public boolean removeTripById(String tripId) {
        return trips.removeIf(t -> t.getId().equals(tripId));
    }
    public Trip getTripById(String tripId) {
        return trips.stream()
                .filter(t -> t.getId().equals(tripId))
                .findFirst()
                .orElse(null);
    }
    public List<Trip> getAllTrips() {
        return new ArrayList<>(trips);
    }

    public List<Trip> searchTrips(String origin, String destination) {
        List<Trip> result = new ArrayList<>();
        for (Trip t : trips) {
            if (t.getOrigin().equalsIgnoreCase(origin) &&
                    t.getDestination().equalsIgnoreCase(destination)) {
                result.add(t);
            }
        }
        return result;
    }
}
