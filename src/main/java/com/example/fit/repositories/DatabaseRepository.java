package com.example.fit.repositories;


import com.example.fit.entities.Account;
import com.example.fit.entities.GrantAccess;
import com.example.fit.entities.Role;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/mysql";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Login to database success!");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.err.println("Login to database failed!");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Close connection.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Close connection failed.");
            }
        }
    }

}