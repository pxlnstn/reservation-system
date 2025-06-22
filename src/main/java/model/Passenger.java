package model;

public class Passenger extends User {
    public Passenger(String id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public String getRole() {
        return "Passenger";
    }
}
