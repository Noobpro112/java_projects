package org.example;

import java.sql.*;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/banco_java", "root", "");
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }
}
