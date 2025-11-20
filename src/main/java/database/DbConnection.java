package database;

import org.junit.jupiter.api.Assertions;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/kasir_pbo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "A1r1y05<>";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
