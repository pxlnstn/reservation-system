package model;

import manager.UserFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryTest {

    @Test
    void createAdminUser() {
        User user = UserFactory.createUser("admin", "0", "admin", "pass");
        assertTrue(user instanceof Admin);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void createPassengerUser() {
        User user = UserFactory.createUser("passenger", "1", "mehmet", "1234");
        assertTrue(user instanceof Passenger);
        assertEquals("mehmet", user.getUsername());
    }



    @Test
    void createInvalidUserType() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserFactory.createUser("unknown", "9", "x", "y");
        });
    }

}
