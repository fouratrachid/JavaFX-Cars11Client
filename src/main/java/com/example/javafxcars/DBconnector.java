package com.example.javafxcars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {

public  static  Connection connectDB ()  {
        // chargement driver
        String driverName = "com.mysql.cj.jdbc.Driver";
        String serverIp = "127.0.0.1";
        String mydatabase = "JavaFXapp";
        String url = "jdbc:mysql://" + serverIp + "/" + mydatabase;

        String username = "root";
        String password = "";
        DriverManager driverManager ;
        Connection connection = null;
        try {
             Class.forName(driverName); // here is the ClassNotFoundException
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database successfully  ");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

    return  connection ;
    }
}
