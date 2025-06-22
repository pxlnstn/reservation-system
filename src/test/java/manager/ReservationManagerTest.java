package manager;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationManagerTest {

    private ReservationManager reservationManager;
    private Trip trip;
    private User user1;
    private User user2;

    @BeforeEach
    void setup() {
        reservationManager = new ReservationManager();
        user1 = new Passenger("1", "mehmet", "1234");
        user2 = new Passenger("2", "ayse", "4321");

        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(new Seat("A1"));
        seats.add(new Seat("A2"));
        trip = new Trip("T1", "Bus", "Izmir", "Ankara", LocalDateTime.now(), seats);
    }

    @Test
    void makeReservation_success() {
        boolean result = reservationManager.makeReservation(user1, trip, "A1");
        assertTrue(result);
        assertTrue(trip.getSeatByNumber("A1").isReserved());
    }

    @Test
    void makeReservation_alreadyReserved() {
        reservationManager.makeReservation(user1, trip, "A1");
        boolean result = reservationManager.makeReservation(user2, trip, "A1");
        assertFalse(result);
    }

    @Test
    void cancelReservation_success() {
        reservationManager.makeReservation(user1, trip, "A2");
        boolean result = reservationManager.cancelReservation(user1, trip, "A2");
        assertTrue(result);
        assertFalse(trip.getSeatByNumber("A2").isReserved());
    }

    @Test
    void cancelReservation_wrongUser() {
        reservationManager.makeReservation(user1, trip, "A2");
        boolean result = reservationManager.cancelReservation(user2, trip, "A2");
        assertFalse(result);
    }
}
