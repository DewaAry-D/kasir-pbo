package controller;

import database.DbConnection;
import amodels.OrderItem;
import amodels.Product;
import amodels.KategoriProduk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class OrderItemController {
    private DbConnection dbConnection;

    public OrderItemController(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public List<OrderItem> getAllOrderItemById(Integer order_id) {
        List<OrderItem> orderList = new ArrayList<>();
        
        String sql = "SELECT " +
            "oi.id AS order_item_id, " +
            "oi.order_id AS order_id, " +
            "oi.product_id AS item_product_id, " + // Alias diubah
            "oi.price AS item_price, " +            // Alias diubah
            "oi.quantity AS item_quantity, " +      // Alias diubah
            "oi.is_done AS item_is_done, " +        // Alias diubah
            "oi.created_at AS item_created_at, " +  // Alias diubah
            "oi.updated_at AS item_updated_at, " +  // Alias diubah

            "p.id AS product_id, " +
            "p.name AS product_name, " +            // Alias diubah
            "p.price AS product_price, " +          // SAYA TAMBAHKAN INI
            "p.description AS product_description, " + // Alias diubah
            "p.foto AS product_foto, " +            // Alias diubah
            "p.category AS product_category, " +    // Alias diubah
            "p.is_active AS product_is_active " +   // Alias diubah

            "FROM order_items oi " +
            "INNER JOIN products p ON oi.product_id = p.id " +
            "WHERE oi.order_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, order_id);
            
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DbConnection db = null;
                    
                    Product product = new Product();
                    
                    product.setId(rs.getInt("product_id")); 
                    product.setName(rs.getString("product_name"));
                    product.setPrice(rs.getDouble("product_price")); 
                    product.setDescription(rs.getString("product_description"));
                    product.setFoto(rs.getString("product_foto"));
                    
                    String kategoriDariDb = rs.getString("product_category");
                    
                    if (kategoriDariDb != null) {
                        try {
                            String kategoriUpper = kategoriDariDb.toUpperCase();

                            KategoriProduk kategori = KategoriProduk.valueOf(kategoriUpper);

                            product.setCategory(kategori);

                        } catch (IllegalArgumentException e) {
                            System.err.println("Kategori tidak valid: " + kategoriDariDb);
                            product.setCategory(null); 
                        }
                    }
        
                    product.setIsActive(rs.getBoolean("product_is_active"));


                    OrderItem item = new OrderItem();
                    
                    item.setId(rs.getInt("order_item_id"));
                    item.setOrderId(rs.getInt("order_id"));
                    item.setProductId(rs.getInt("item_product_id"));
                    item.setPrice(rs.getDouble("item_price"));
                    item.setQuantity(rs.getInt("item_quantity"));
                    item.setDone(rs.getBoolean("item_is_done"));
                    item.setCreatedAt(rs.getTimestamp("item_created_at"));
                    item.setUpdatedAt(rs.getTimestamp("item_updated_at"));
                    
                    item.setProduct(product);

                    orderList.add(item);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error saat mengambil data order: " + e.getMessage());
            e.printStackTrace();
        }

        return orderList;
    }
    
    public void updateStatusById(Integer orderItem_id, Boolean status) {
        String sql = "UPDATE order_items SET is_done = ? where id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setBoolean(1, status);
            pstmt.setInt(2, orderItem_id);

            int barisTerpengaruh = pstmt.executeUpdate();
            
            if (barisTerpengaruh == 1) {
                System.out.println("Update berhasil untuk item ID: " + orderItem_id);
            } else {
                System.out.println("Update gagal. Item ID: " + orderItem_id + " tidak ditemukan.");
            }
            
        } catch(SQLException e) {
            System.err.println("Error saat update status orderitem: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
