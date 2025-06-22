package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import manager.TripManager;
import model.Trip;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class AdminPanelScreen {

    private Stage stage;
    private TripManager tripManager;

    public AdminPanelScreen(Stage stage, TripManager tripManager) {
        this.stage = stage;
        this.tripManager = tripManager;
    }

    public void show() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Admin Panel - Trip Management");

        // Input fields
        TextField originField = new TextField();
        originField.setPromptText("Origin");

        TextField destinationField = new TextField();
        destinationField.setPromptText("Destination");

        TextField typeField = new TextField();
        typeField.setPromptText("Type (Bus/Flight)");

        DatePicker datePicker = new DatePicker();

        Button addTripButton = new Button("Add Trip");

        // TableView
        TableView<Trip> tripTable = new TableView<>();
        ObservableList<Trip> tripList = FXCollections.observableArrayList(tripManager.getAllTrips());

        TableColumn<Trip, String> originCol = new TableColumn<>("Origin");
        originCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getOrigin()));

        TableColumn<Trip, String> destCol = new TableColumn<>("Destination");
        destCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDestination()));

        TableColumn<Trip, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));

        TableColumn<Trip, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDateTime().toString()));

        tripTable.getColumns().addAll(originCol, destCol, typeCol, dateCol);
        tripTable.setItems(tripList);

        Button deleteTripButton = new Button("Delete Selected Trip");

        addTripButton.setOnAction(e -> {
            String id = UUID.randomUUID().toString();
            String origin = originField.getText();
            String dest = destinationField.getText();
            String type = typeField.getText();
            LocalDateTime date = datePicker.getValue() != null ? datePicker.getValue().atStartOfDay() : LocalDateTime.now();

            Trip newTrip = new Trip(id, type, origin, dest, date, new ArrayList<>());
            tripManager.addTrip(newTrip);
            tripList.setAll(tripManager.getAllTrips());
        });

        deleteTripButton.setOnAction(e -> {
            Trip selected = tripTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                tripManager.removeTripById(selected.getId());
                tripList.setAll(tripManager.getAllTrips());
            }
        });

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.addRow(0, new Label("Origin:"), originField);
        inputGrid.addRow(1, new Label("Destination:"), destinationField);
        inputGrid.addRow(2, new Label("Type:"), typeField);
        inputGrid.addRow(3, new Label("Date:"), datePicker);
        inputGrid.add(addTripButton, 1, 4);

        // Logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen(stage, new auth.AuthService());
            loginScreen.show();
        });

        HBox logoutBox = new HBox(logoutButton);
        logoutBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel, inputGrid, tripTable, deleteTripButton, logoutBox);

        Scene scene = new Scene(root, 650, 550);
        stage.setScene(scene);
        stage.setTitle("Admin Panel");
        stage.show();
    }
}
