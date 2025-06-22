package manager;

import model.Admin;
import model.Passenger;
import model.User;

public class UserFactory {

    public static User createUser(String type, String id, String username, String password) {
        switch (type.toLowerCase()) {
            case "passenger":
                return new Passenger(id, username, password);
            case "admin":
                return new Admin(id, username, password);
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}

