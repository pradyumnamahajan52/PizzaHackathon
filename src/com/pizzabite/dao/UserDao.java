package com.pizzabite.dao;

import com.pizzabite.entity.User;
import com.pizzabite.sql.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements AutoCloseable {

    private Connection connection;

    public UserDao() throws SQLException {
        connection = DbConnection.getConnection();
    }

    public int UserRegistration(User user) throws SQLException {

        String sql = "insert into pizza_customers(name,password,mobile,address,email) values(?,?,?,?,?)";
        try (PreparedStatement preparedstatement = connection.prepareStatement(sql)) {
            preparedstatement.setString(1, user.getName());
            preparedstatement.setString(2, user.getPassword());
            preparedstatement.setString(3, user.getMobile());
            preparedstatement.setString(4, user.getAddress());
            preparedstatement.setString(5, user.getEmail());
            return preparedstatement.executeUpdate();
        }

    }

    public User UserLogin(User user) throws SQLException {
        String sql = "SELECT id, name, mobile, address, email, admin FROM pizza_customers WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User foundUser = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("mobile"), resultSet.getString("address"), resultSet.getString("email"), resultSet.getBoolean("admin"));

                    return foundUser;
                } else {

                    return null;
                }
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }

    }
}
