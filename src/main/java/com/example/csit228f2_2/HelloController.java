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

public class HelloController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private Button submitButton;

    @FXML
    private Button listButton;

    @FXML
    private void initialize() {
        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String email = emailField.getText();

            insertDataIntoDatabase(name, email);
        });
    }

    private void insertDataIntoDatabase(String name, String email) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (name, email) VALUES (?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, email);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Data Inserted Successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleListButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) listButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("user-lists-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}