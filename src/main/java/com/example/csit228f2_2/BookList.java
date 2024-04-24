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

public class BookList{

    private int id;

    @FXML
    private Button adminButton;

    @FXML
    private TextField bookid;

    @FXML
    private Button confirmButton;

    @FXML
    private Button back3;

    @FXML
    private Button addbookButton;

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Integer> bidColumn;

    @FXML
    private TableColumn<Book, String> titColumn;

    @FXML
    private TableColumn<Book, String> genColumn;
    @FXML
    private TableColumn<Book, String> availColumn;

    @FXML
    private Button confirmButton3;

    @FXML
    private TextField bookfield;

    public BookList(){

    }
    public BookList(int id){
        this.id = id;
    }


    private RetrieveData dataAccess = new RetrieveData();

    public void initialize() {
        bidColumn.setCellValueFactory(cellData -> cellData.getValue().bidProperty().asObject());
        titColumn.setCellValueFactory(cellData -> cellData.getValue().titProperty());
        genColumn.setCellValueFactory(cellData -> cellData.getValue().genProperty());
        availColumn.setCellValueFactory(cellData -> cellData.getValue().availProperty());

        populateTable();
    }

    private void populateTable() {
        List<Book> bookList = dataAccess.getAllBooks();
        ObservableList<Book> observableList = FXCollections.observableArrayList(bookList);
        bookTable.setItems(observableList);
    }


    @FXML
    private void handleListButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) adminButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goBackClick3(ActionEvent event) throws IOException {
        Stage stage = (Stage) back3.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleAddBookButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) addbookButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("book-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void confirmButtonClick(ActionEvent event) throws IOException {
        String txtbookid = bookid.getText();

        int bookid = Integer.parseInt(txtbookid);

        System.out.println(id);

        try(Connection c = MySQLConnection.getConnection()) {
            c.setAutoCommit(false);

            try(PreparedStatement statement1 = c.prepareStatement(
                    "UPDATE books SET availability='unavailable' WHERE book_id=?")) {
                statement1.setInt(1, bookid);

                statement1.executeUpdate();

                c.commit();
                c.close();
            }

            try(PreparedStatement statement2 = c.prepareStatement(
                    "INSERT INTO borrowedbooks (id, book_id) VALUES (?, ?)")) {
                statement2.setInt(1, id);
                statement2.setInt(2, bookid);

                statement2.executeUpdate();
                c.commit();
                c.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void confirmButtonClick3(ActionEvent event) throws IOException {
        String txtbook = bookfield.getText();

        int bookid = Integer.parseInt(txtbook);

        try(Connection c = MySQLConnection.getConnection()){
                c.setAutoCommit(false);

            try(PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM books WHERE book_id=?")) {
                statement.setInt(1, bookid);

                statement.executeUpdate();

                c.commit();
                c.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        Stage stage = (Stage) confirmButton3.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("booklist-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
