package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class MySqlConnection {
    private final String URL = "jdbc:mysql://localhost:3306/E-TicketingPlatform";
    private final String USER = "root";
    private final String PASSWORD = "1234567";
    private Connection conn;
    static MySqlConnection instance = null;

    private MySqlConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the DataBase successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static public MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
