package com.karthigajspjdbc.dao;

import com.karthigajspjdbc.db.DBConnection;
import com.karthigajspjdbc.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private final Connection connection;

    public UserDao() {

        this.connection= DBConnection.getConnection();
    }

    private static final String SELECT_QUERY = "SELECT id,username,password FROM auth WHERE username=? AND password=?;";
    private static final String SELECT_USER_QUERY = "SELECT id,username,password FROM auth WHERE id=?;";

    private static final String INSERT_USER_QUERY = "INSERT INTO auth (username,password) VALUES(?,?);";
    private static final String SELECT_ALL_USERS = "SELECT username FROM auth;";
    private static final String SELECT_USER_NAME = "SELECT username FROM auth WHERE id=?;;";


    public User loggedInUser(String username, String password) {
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public void registerUser(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getUser(String username) {
        boolean isExistingUser=false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                if(username.trim().equals(resultSet.getString("username")))
                {
                    isExistingUser=true;break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExistingUser;
    }

    public String getName(int userId) {
        String name="";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_NAME);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                name = resultSet.getString("username");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;

    }
}
