package model;

import observer.SeatObserver;

import java.util.ArrayList;
import java.util.List;

public class Seat {
    private String seatNumber;
    private boolean isReserved;
    private User reservedBy;

    private List<SeatObserver> observers = new ArrayList<>();

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.isReserved = false;
        this.reservedBy = null;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public User getReservedBy() {
        return reservedBy;
    }

    public void reserve(User user) {
        this.isReserved = true;
        this.reservedBy = user;
        notifyObservers();
    }

    public void cancel() {
        this.isReserved = false;
        this.reservedBy = null;
        notifyObservers();
    }

    public void addObserver(SeatObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (SeatObserver observer : observers) {
            observer.onSeatStatusChanged(seatNumber);
        }
    }
}
