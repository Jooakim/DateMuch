import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;

public class MainDate extends Application {

    Stage window;
    Scene scene;

    ConnectDB database;
    Connection databaseConnection;
    String username, password;

    public static void main(String[] args) {
        launch(args);
    }    

    @Override
    public void start(Stage primaryStage) throws Exception {
        database = new ConnectDB();
        databaseConnection = database.getConnection();
        window = primaryStage;
        window.setTitle("Date Much?!");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });



        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Username
        Label nameLabel = new Label("Username");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        // Password
        Label passLabel = new Label("Password");
        GridPane.setConstraints(passLabel, 0, 1);

        TextField passInput = new TextField();
        passInput.setPromptText("Password");
        GridPane.setConstraints(passInput, 1, 1);

        // Login button
        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            username = nameInput.getText();
            password = passInput.getText();
            login(username, password);
        });

        // Register button
        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 1,3);
        registerButton.setOnAction(e -> {
            RegisterPage register = new RegisterPage(window, databaseConnection);
        });

        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, registerButton);

        scene = new Scene(grid, 500, 400);

        window.setScene(scene);
        window.show();


    }

    private void closeProgram() {
        boolean answer = ConfirmBox.display("Title", "Sure?");
        if(answer)
            window.close();

    }

    private boolean isInt(TextField input, String message) {

        try {

            int age = Integer.parseInt(message);
            System.out.println("User is: " + age);
            return true;

        } catch(NumberFormatException e){

            System.out.println("Not a number");
            return false;
        }
    }

    private void login(String name, String pass) {
        String sql = "SELECT * FROM USERS WHERE username = '" + name + "' AND password = '" + pass + "';";
        try {
            Statement stmt = databaseConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                ProfilePage profile = new ProfilePage(window, name);
            } else {
                AlertBox.display("Error", "No user with that username/password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
