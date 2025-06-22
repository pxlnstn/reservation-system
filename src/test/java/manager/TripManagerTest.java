package manager;

import model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TripManagerTest {

    private TripManager tripManager;

    @BeforeEach
    void setup() {
        tripManager = TripManager.getInstance();
        tripManager.getAllTrips().clear(); // temiz başla
    }

    @Test
    void testAddTrip() {
        Trip trip = new Trip("T1", "Bus", "Izmir", "Ankara", LocalDateTime.now(), new ArrayList<>());
        tripManager.addTrip(trip);

        assertEquals(1, tripManager.getAllTrips().size());
        assertEquals("T1", tripManager.getAllTrips().get(0).getId());
    }

    @Test
    void testRemoveTrip() {
        Trip trip = new Trip("T2", "Flight", "Istanbul", "Berlin", LocalDateTime.now(), new ArrayList<>());
        tripManager.addTrip(trip);
        boolean removed = tripManager.removeTripById("T2");

        assertTrue(removed);
        assertNull(tripManager.getTripById("T2"));
    }

    @Test
    void testSearchTrip() {
        Trip trip = new Trip("T3", "Train", "Paris", "Rome", LocalDateTime.now(), new ArrayList<>());
        tripManager.addTrip(trip);

        var results = tripManager.searchTrips("Paris", "Rome");
        assertEquals(1, results.size());
        assertEquals("T3", results.get(0).getId());
    }
}
