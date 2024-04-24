package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] args){
        createTables();
    }
    private static void createTables(){
        try(Connection c = MySQLConnection.getConnection();
            Statement statement = c.createStatement()) {

            c.setAutoCommit(false);

            String createTableQuery1 = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "firstname VARCHAR(50) NOT NULL, " +
                    "lastname VARCHAR(50) NOT NULL, " +
                    "username VARCHAR(50) NOT NULL, " +
                    "password VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL)";
            statement.execute(createTableQuery1);
            System.out.println("Users table created successfully!");

            String createTableQuery2 = "CREATE TABLE IF NOT EXISTS books (" +
                    "book_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "title VARCHAR(50) NOT NULL, " +
                    "genre VARCHAR(50) NOT NULL, " +
                    "availability VARCHAR(50) DEFAULT 'available')";
            statement.execute(createTableQuery2);
            System.out.println("Books table created successfully!");

            String createTableQuery3 = "CREATE TABLE IF NOT EXISTS borrowedbooks (" +
                    "borrow_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "id INT, " +
                    "book_id INT, " +
                    "FOREIGN KEY (id) REFERENCES users(id), " +
                    "FOREIGN KEY (book_id) REFERENCES books(book_id))";
            statement.execute(createTableQuery3);
            System.out.println("Borrowed books table created successfully!");

            c.commit();
            c.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
