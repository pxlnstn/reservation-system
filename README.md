# Reservation System

A Java-based Bus and Flight Reservation System with JavaFX GUI and Object-Oriented Design Patterns, providing an interactive and modular booking experience.

## ✨ Features

* **User Authentication**: Login & Register as Admin or Passenger
* **Admin Panel**: Add/Delete trips with origin, destination, type, and date
* **Trip Listing**: Passengers can view, search, and select available trips
* **Seat Reservation GUI**:

  * ⬛ Available Seats
  * ⚫ Reserved by Others
  * 🟧 Reserved by Current User (Cancellable)
* **Reservation Cancellation**: Click your seat again to cancel
* **Real-time GUI Update**: Seat changes reflected dynamically
* **Trip Search**: Search trips by origin, destination, or type

## 🧬 Design Patterns Used

* **Factory Pattern**: `UserFactory` dynamically creates Admin or Passenger instances based on role
* **Singleton Pattern**: `TripManager` ensures centralized trip storage throughout the application
* **Observer Pattern**: `Seat` is observable; `SeatScreen` observes and updates GUI when seat changes
* **Strategy Pattern**: `ReservationStrategy` interface allows switching between reservation and cancellation behavior via `StrategyContext`

These patterns improve modularity, maintainability, and clarity.

## 🚀 Technologies

* Java 17–24
* JavaFX 21.0.2
* Maven
* JUnit 5.10.0

## 💡 How to Run

1. Clone the repository
2. Ensure JavaFX SDK is downloaded and linked
3. From IntelliJ:

   * Add JavaFX `lib/` to Libraries
   * Set VM options:

     ```
     --module-path "PATH_TO_JAVAFX/lib" --add-modules javafx.controls,javafx.fxml
     ```
4. Run `Main.java` (GUI starts at Login)

Alternatively, from terminal:

```bash
mvn javafx:run
```

## 🔎 How to Search Trips

* Use the search box on the Trip List screen
* Filters by origin, destination, or type

## 🥇 Testing

Run unit tests with:

```bash
mvn test
```

Tested components include:

* Reservation logic
* Trip management
* User object creation via factory




---

This project is a coursework assignment for AOOP and demonstrates strong OOP practices, GUI development, and software design principles.
