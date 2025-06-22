package app;

import auth.AuthService;
import gui.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private final AuthService authService = new AuthService();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginScreen loginScreen = new LoginScreen(primaryStage, authService);
        loginScreen.show();
    }
}
