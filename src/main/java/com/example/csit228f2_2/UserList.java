package com.example.csit228f2_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserList{

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> fnameColumn;

    @FXML
    private TableColumn<User, String> lnameColumn;

    @FXML
    private TableColumn<User, String> unameColumn;


    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private Button back2;

    @FXML
    private Button confirmButton2;

    @FXML
    private TextField userid;

    @FXML
    private Button booklistButton;

    private RetrieveData dataAccess = new RetrieveData();

    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        fnameColumn.setCellValueFactory(cellData -> cellData.getValue().fnameProperty());
        lnameColumn.setCellValueFactory(cellData -> cellData.getValue().lnameProperty());
        unameColumn.setCellValueFactory(cellData -> cellData.getValue().unameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        populateTable();
    }

    private void populateTable() {
        List<User> userList = dataAccess.getAllUsers();
        ObservableList<User> observableList = FXCollections.observableArrayList(userList);
        userTable.setItems(observableList);
    }

    @FXML
    private void goBackClick2(ActionEvent event) throws IOException {
        Stage stage = (Stage) back2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBookListButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) booklistButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("booklist-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void confirmButtonClick2(ActionEvent event) throws IOException {
        String txtuserid = userid.getText();

        int userid = Integer.parseInt(txtuserid);

        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM users WHERE id=?")) {
            c.setAutoCommit(false);
            statement.setInt(1, userid);

            statement.executeUpdate();

            c.commit();
            c.close();

        } catch (SQLException e){
            e.printStackTrace();
        }


        Stage stage = (Stage) confirmButton2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
