import java.sql.*;

import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.*;

public class RegisterPage {

    Stage window;

    Connection databaseConnection;
    Scene loginScene;

    public RegisterPage(Stage window, Connection databaseConnection) {
        this.window = window;
        this.databaseConnection = databaseConnection;
        this.loginScene = window.getScene();
        setScene();
    }

    private void setScene() {
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

        // Email
        Label emailLabel = new Label("Email");
        GridPane.setConstraints(emailLabel, 0, 2);

        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 2);

        // Age
        Label ageLabel = new Label("Age");
        GridPane.setConstraints(ageLabel, 0, 3);

        TextField ageInput = new TextField();
        GridPane.setConstraints(ageInput, 1, 3);

        // Submit button
        Button submitRegistration = new Button("Submit");
        GridPane.setConstraints(submitRegistration, 1, 4);
        submitRegistration.setOnAction(e -> {
            submitRegistration(nameInput.getText(), passInput.getText(), emailInput.getText(), Integer.parseInt(ageInput.getText()));
        });


        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, emailLabel, emailInput, ageLabel, ageInput, submitRegistration);

        Scene registrationScene= new Scene(grid, 500, 400);

        window.setScene(registrationScene);
        window.show();
    }

    private void submitRegistration(String username, String password, String email, int age) {

        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println(age);

        String sql = "INSERT INTO USERS VALUES ( "
            + " '" + username + "', "
            + " '" + password + "', "
            + " '" + email + "', "
            + " " + age + ");";

        try { Statement stmt = databaseConnection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            databaseConnection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            AlertBox.display("Registration", "Registration Failed");
        }
            AlertBox.display("Registration", "Registration Complete");
            window.setScene(loginScene);

    }
}
