package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatTest {

    private Seat seat;
    private User user;

    @BeforeEach
    void setup() {
        seat = new Seat("B1");
        user = new Passenger("2", "ayse", "1234");
    }

    @Test
    void reserveAndCheckStatus() {
        seat.reserve(user);
        assertTrue(seat.isReserved());
        assertEquals(user, seat.getReservedBy());
    }

    @Test
    void cancelReservation() {
        seat.reserve(user);
        seat.cancel();
        assertFalse(seat.isReserved());
        assertNull(seat.getReservedBy());
    }
}
