package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveData {

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = statement.executeQuery()) {
            c.setAutoCommit(false);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fname = resultSet.getString("firstname");
                String lname = resultSet.getString("lastname");
                String uname = resultSet.getString("username");
                String email = resultSet.getString("email");
                User user = new User(id, fname, lname, uname, email);
                userList.add(user);
            }

            c.commit();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("SELECT * FROM books");
             ResultSet resultSet = statement.executeQuery()) {

            c.setAutoCommit(false);

            while (resultSet.next()) {
                int bid = resultSet.getInt("book_id");
                String tit = resultSet.getString("title");
                String gen = resultSet.getString("genre");
                String avail = resultSet.getString("availability");
                Book book = new Book(bid, tit, gen, avail);
                bookList.add(book);
            }

            c.commit();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

}
