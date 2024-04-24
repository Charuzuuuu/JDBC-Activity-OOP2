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

public class InsertBook {

    @FXML
    private TextField titleField;

    @FXML
    private TextField genreField;

    @FXML
    private Button back4;

    @FXML
    private void addBook(ActionEvent event) {
        String title = titleField.getText();
        String genre = genreField.getText();

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO books (title, genre) VALUES (?, ?)")) {
            c.setAutoCommit(false);
            statement.setString(1, title);
            statement.setString(2, genre);
            int rowsInserted = statement.executeUpdate();


            c.commit();

            if (rowsInserted > 0) {
                System.out.println("Book Inserted Successfully!");
            }

            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBackClick4(ActionEvent event) throws IOException {
        Stage stage = (Stage) back4.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
