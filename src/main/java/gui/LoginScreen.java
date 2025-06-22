package gui;

import auth.AuthService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import manager.TripManager;
import model.Seat;
import model.Trip;
import model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginScreen {

    private final AuthService authService;
    private final Stage stage;

    public LoginScreen(Stage stage, AuthService authService) {
        this.stage = stage;
        this.authService = authService;
    }

    public void show() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label messageLabel = new Label();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = authService.login(username, password);
            if (user != null) {
                messageLabel.setText("Login successful: " + user.getUsername());

                TripManager tripManager = TripManager.getInstance();

                if (user.getRole().equals("Admin")) {
                    AdminPanelScreen adminScreen = new AdminPanelScreen(stage, tripManager);
                    adminScreen.show();
                } else {
                    // Sadece ilk kez giriş yapıldığında seferleri ve koltukları ekle
                    if (tripManager.getAllTrips().isEmpty()) {
                        List<Seat> seats1 = new ArrayList<>();
                        List<Seat> seats2 = new ArrayList<>();

                        for (int i = 0; i < 4; i++) {
                            for (int j = 1; j <= 5; j++) {
                                String seatNum = (char) ('A' + i) + String.valueOf(j);
                                seats1.add(new Seat(seatNum));
                                seats2.add(new Seat(seatNum));
                            }
                        }

                        tripManager.addTrip(new Trip(
                                "T1", "Bus", "Izmir", "Ankara",
                                LocalDateTime.now().plusDays(1), seats1
                        ));

                        tripManager.addTrip(new Trip(
                                "T2", "Flight", "Istanbul", "Berlin",
                                LocalDateTime.now().plusDays(2), seats2
                        ));
                    }

                    TripListScreen tripListScreen = new TripListScreen(stage, tripManager, user);
                    tripListScreen.show();
                }

            } else {
                messageLabel.setText("Invalid username or password");
            }
        });




        Button registerButton = new Button("Register");
        registerButton.setOnAction(ev -> {
            RegisterScreen registerScreen = new RegisterScreen(stage, authService);
            registerScreen.show();
        });

        root.getChildren().add(registerButton);


        root.getChildren().addAll(
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                loginButton,
                messageLabel
        );

        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}
