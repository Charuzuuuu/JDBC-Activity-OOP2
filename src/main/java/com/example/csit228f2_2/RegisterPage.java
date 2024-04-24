package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterPage {

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField uname;

    @FXML
    private TextField pword;

    @FXML
    private TextField emailField;

    @FXML
    private Button submitButton;

    @FXML
    private Button back1;

    @FXML
    private void initialize() {
        submitButton.setOnAction(event -> {
            String firstname = fname.getText();
            String lastname = lname.getText();
            String username = uname.getText();
            String password = pword.getText();
            String email = emailField.getText();

            insertDataIntoDatabase(firstname, lastname, username, password, email);
        });
    }

    private void insertDataIntoDatabase(String firstname, String lastname, String username, String password, String email) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (firstname, lastname, username, password, email) VALUES (?, ?, ?, ?, ?)")) {
            c.setAutoCommit(false);

            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setString(5, email);
            int rowsInserted = statement.executeUpdate();

            c.commit();

            if (rowsInserted > 0) {
                System.out.println("Data Inserted Successfully!");
            }

            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) back1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}