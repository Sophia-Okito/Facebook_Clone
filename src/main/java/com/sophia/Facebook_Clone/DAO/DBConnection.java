package com.sophia.Facebook_Clone.DAO;

import java.sql.*;


public class DBConnection {
    // method to create a new database connection
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbUrl  = "jdbc:mysql://localhost:3306/Facebook?serverTimezone=UTC";
        String dbUsername ="root";
        String dbPassword = ""; //Mas10ter^^^&@$$%%%%
        // returns the newly created database connection
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
