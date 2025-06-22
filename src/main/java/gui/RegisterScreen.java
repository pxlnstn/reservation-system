package gui;

import auth.AuthService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class RegisterScreen {

    private final Stage stage;
    private final AuthService authService;

    public RegisterScreen(Stage stage, AuthService authService) {
        this.stage = stage;
        this.authService = authService;
    }

    public void show() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("passenger", "admin");
        roleBox.setValue("passenger");

        Button registerButton = new Button("Register");
        Label messageLabel = new Label();

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleBox.getValue();

            boolean success = authService.register(role, String.valueOf(System.currentTimeMillis()), username, password);

            if (success) {
                messageLabel.setText("Registration successful!");
            } else {
                messageLabel.setText("Username already exists.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen(stage, authService);
            loginScreen.show();
        });

        root.getChildren().addAll(new Label("Register New User"), usernameField, passwordField, roleBox, registerButton, messageLabel, backButton);

        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }
}
