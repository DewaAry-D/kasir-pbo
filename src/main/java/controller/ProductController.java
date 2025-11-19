package controller;

import database.DbConnection;
import amodels.Product;
import amodels.KategoriProduk;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class ProductController {
    private DbConnection db;
    
    public ProductController (DbConnection db) {
        this.db = db;
    }
    
    public List<Product> getAllProducts () {
        List<Product> listProduk = new ArrayList<>();
        String sql = "Select * FROM products WHERE is_active = 1";
        
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                p.setFoto(rs.getString("foto"));
                
                String catStr = rs.getString("category");
                if (catStr != null) {
                    try {
                        p.setCategory(KategoriProduk.valueOf(catStr.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Warning: Kategori tidak dikenal di DB: " + catStr);
                    }
                }

                p.setIsActive(rs.getBoolean("is_active"));
                p.setCreatedAt(rs.getTimestamp("created_at"));
                p.setUpdatedAt(rs.getTimestamp("updated_at"));
                
                listProduk.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProduk;
    }
    
    public boolean hapusProduk (int idProduk) {
        String sql = "UPDATE products SET is_active = 0 WHERE id = ?";
        
        try (Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProduk);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
