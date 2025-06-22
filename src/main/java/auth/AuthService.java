package auth;

import manager.UserFactory;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users;

    public AuthService() {
        this.users = new HashMap<>();
        // Predefined users:
        users.put("admin", UserFactory.createUser("admin", "0", "admin", "admin123"));
        users.put("mehmet", UserFactory.createUser("passenger", "1", "mehmet", "1234"));
    }

    public boolean register(String type, String id, String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        User user = UserFactory.createUser(type, id, username, password);
        users.put(username, user);
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Map<String, User> getAllUsers() {
        return users;
    }
}
