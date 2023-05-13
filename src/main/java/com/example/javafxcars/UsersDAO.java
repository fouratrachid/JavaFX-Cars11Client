package com.example.javafxcars;

import com.example.javafxcars.DBconnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {
    public static ResultSet GETUsers()
    {
        try {
            Connection conn = DBconnector.connectDB();
            String sql = "SELECT *"+
                    "FROM users  " ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            return  preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
