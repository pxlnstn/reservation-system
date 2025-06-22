package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import manager.ReservationManager;
import manager.TripManager;
import model.Seat;
import model.Trip;
import model.User;
import observer.SeatObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatScreen implements SeatObserver {

    private Stage stage;
    private Trip trip;
    private User user;
    private ReservationManager reservationManager;
    private Map<String, Button> seatButtons = new HashMap<>();

    public SeatScreen(Stage stage, Trip trip, User user, ReservationManager reservationManager) {
        this.stage = stage;
        this.trip = trip;
        this.user = user;
        this.reservationManager = reservationManager;
    }

    public void show() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Seats for Trip: " + trip.getId());
        Label infoLabel = new Label("Click a white seat to reserve. Click your orange seat again to cancel.");
        infoLabel.setStyle("-fx-font-style: italic; -fx-text-fill: gray;");


        GridPane seatGrid = new GridPane();
        seatGrid.setHgap(10);
        seatGrid.setVgap(10);
        seatGrid.setAlignment(Pos.CENTER);

        List<Seat> seats = trip.getSeats();


        int row = 0;
        int col = 0;

        for (Seat seat : seats) {
            Button seatBtn = new Button(seat.getSeatNumber());
            seatBtn.setMinSize(50, 30);

            seat.addObserver(this);
            seatButtons.put(seat.getSeatNumber(), seatBtn);

            updateButtonStyle(seatBtn, seat);

            seatBtn.setOnAction(e -> {
                strategy.StrategyContext context = new strategy.StrategyContext();

                if (seat.isReserved()) {
                    if (seat.getReservedBy().equals(user)) {
                        context.setStrategy(new strategy.CancelReservationStrategy(reservationManager));
                        context.apply(user, trip, seat.getSeatNumber());
                    }
                } else {
                    context.setStrategy(new strategy.NormalReservationStrategy(reservationManager));
                    context.apply(user, trip, seat.getSeatNumber());
                }
            });


            seatGrid.add(seatBtn, col, row);

            col++;
            if (col >= 5) {
                col = 0;
                row++;
            }
        }

        Button backButton = new Button("Back to Trips");
        backButton.setOnAction(e -> {
            TripListScreen tripListScreen = new TripListScreen(stage, TripManager.getInstance(), user);
            tripListScreen.show();
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen(stage, new auth.AuthService());
            loginScreen.show();
        });

        HBox buttonBox = new HBox(15, backButton, logoutButton);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel, infoLabel, seatGrid, buttonBox);

        Scene scene = new Scene(root, 500, 450);
        stage.setScene(scene);
        stage.setTitle("Seat Selection");
        stage.show();
    }
    private void updateButtonStyle(Button btn, Seat seat) {
        if (seat.isReserved()) {
            if (seat.getReservedBy().equals(user)) {
                btn.setStyle("-fx-background-color: orange");
            } else {
                btn.setStyle("-fx-background-color: gray");
                btn.setDisable(true);
            }
        } else {
            btn.setStyle("");
            btn.setDisable(false);
        }
    }
    @Override
    public void onSeatStatusChanged(String seatNumber) {
        Button btn = seatButtons.get(seatNumber);
        if (btn != null) {
            Seat seat = trip.getSeatByNumber(seatNumber);
            updateButtonStyle(btn, seat);
        }
    }
}
