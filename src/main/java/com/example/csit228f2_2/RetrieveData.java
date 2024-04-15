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
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                User user = new User(id, name, email);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
