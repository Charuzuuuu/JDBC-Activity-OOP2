package com.example.csit228f2_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UserList{

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    private RetrieveData dataAccess = new RetrieveData();

    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        populateTable();
    }

    private void populateTable() {
        List<User> userList = dataAccess.getAllUsers();
        ObservableList<User> observableList = FXCollections.observableArrayList(userList);
        userTable.setItems(observableList);
    }

}
