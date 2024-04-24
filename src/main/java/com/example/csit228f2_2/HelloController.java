package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private Button regButton;

    @FXML
    private void registerButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) regButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("database-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loginButtonClick(ActionEvent event) throws IOException{
        String username = user.getText();
        String password = pass.getText();

        int userId = authenticateUser(username, password);

        if (userId != -1) {
            showAlert("Login Successful", "Welcome, " + username + "!");
            BookList bk = new BookList(userId);
            System.out.println(userId);
            Stage stage = (Stage) regButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private int authenticateUser(String username, String password) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "SELECT id FROM users WHERE username = ? AND password = ?")) {

            c.setAutoCommit(false);

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

            c.commit();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}