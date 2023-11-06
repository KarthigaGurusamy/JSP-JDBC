package com.karthigajspjdbc.dao;

import com.karthigajspjdbc.db.DBConnection;
import com.karthigajspjdbc.models.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {


    private final Connection connection;

    public TodoDao() {

        this.connection = DBConnection.getConnection();
    }

    private static final String INSERT_ITEM = "INSERT INTO todo(userId,item) VALUES (?,?);";
    private static final String SELECT_ALL_ITEMS = "SELECT id,userId,item FROM todo WHERE userId=? AND delete_flag=false;";

    private static final String DELETE_ITEM = "UPDATE todo SET delete_flag=true WHERE id=?;";



    public void AddItem(String item, int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, item);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Todo> selectAllTodos(int userId) {
        List<Todo> items = new ArrayList<Todo>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ITEMS);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Todo todo = new Todo();
                todo.setId(Integer.parseInt(rs.getString("id")));
                todo.setItem(rs.getString("item"));
                todo.setUserId(rs.getInt("userId"));
                items.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(items.size());
        return items;
    }

    public void deleteTodo(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_ITEM);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
