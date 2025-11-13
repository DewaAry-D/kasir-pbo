package database;

import org.junit.jupiter.api.Assertions;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
    Connection connection;
    public DbConnection(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/kasir_pbo";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Berhasil terkoneksi degan database kasir_pbo");
//            connection.close();
//            System.out.println("Berhasil menutup koneksi");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
    
    public Connection getConnection(){
       return connection; 
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi berhasil ditutup.");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi. Pesan: " + e.getMessage());
        }
    }
       
}
