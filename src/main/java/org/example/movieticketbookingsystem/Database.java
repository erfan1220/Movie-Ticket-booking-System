package org.example.movieticketbookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection CODB() throws SQLException {
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/Movie","root","");
        connect.setAutoCommit(true);
        return connect;
    }
}
