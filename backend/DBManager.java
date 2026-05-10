package com.courierms.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

    private static final String URL = "jdbc:mysql://localhost:3306/courier_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected!");
            return con;
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}