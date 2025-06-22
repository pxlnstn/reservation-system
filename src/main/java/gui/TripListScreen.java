package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import manager.ReservationManager;
import manager.TripManager;
import model.Trip;
import model.User;

public class TripListScreen {

    private Stage stage;
    private TripManager tripManager;
    private User user;

    public TripListScreen(Stage stage, TripManager tripManager, User user) {
        this.stage = stage;
        this.tripManager = tripManager;
        this.user = user;
    }

    public void show() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Available Trips");

        TextField searchField = new TextField();
        searchField.setPromptText("Search trips (origin/destination/type)");

        TableView<Trip> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Trip, String> originCol = new TableColumn<>("Origin");
        originCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrigin()));

        TableColumn<Trip, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDestination()));

        TableColumn<Trip, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateTime().toString()));

        TableColumn<Trip, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));

        tableView.getColumns().addAll(originCol, destinationCol, dateCol, typeCol);

        ObservableList<Trip> tripList = FXCollections.observableArrayList(tripManager.getAllTrips());
        tableView.setItems(tripList);

        FilteredList<Trip> filteredList = new FilteredList<>(tripList, p -> true);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredList.setPredicate(trip -> {
                if (newVal == null || newVal.isEmpty()) return true;
                String lower = newVal.toLowerCase();
                return trip.getOrigin().toLowerCase().contains(lower) ||
                        trip.getDestination().toLowerCase().contains(lower) ||
                        trip.getType().toLowerCase().contains(lower);
            });
        });

        tableView.setItems(filteredList);



        Button viewSeatsButton = new Button("View Seats");
        viewSeatsButton.setOnAction(e -> {
            Trip selectedTrip = tableView.getSelectionModel().getSelectedItem();
            if (selectedTrip != null) {
                SeatScreen seatScreen = new SeatScreen(stage, selectedTrip, user, new ReservationManager());
                seatScreen.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a trip.");
                alert.showAndWait();
            }
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen(stage, new auth.AuthService());
            loginScreen.show();
        });

        HBox buttonBox = new HBox(15, viewSeatsButton, logoutButton);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel, searchField, tableView, buttonBox);

        Scene scene = new Scene(root, 600, 450);
        stage.setScene(scene);
        stage.setTitle("Trip List");
        stage.show();
    }
}
