package com.ikechukwu.social_network.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
The DBConnection class is used to create connection instance for connection to our database
 */
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get connection to the database with the given parameters
            /*
                url, username and password of database
            */
            String url = "jdbc:mysql://localhost:3306/testdb";
            String username = "root";
            String password = "password";
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    //method to create a connection instance
    public static DBConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }
}
