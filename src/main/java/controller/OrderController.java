package controller;

import database.DbConnection;
import amodels.Order;
import amodels.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderController {

    private DbConnection dbConnection;

    public OrderController(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Order> getAllPendingOrders() {
        List<Order> orderList = new ArrayList<>();
        
        String sql = "SELECT * FROM orders WHERE status = 'pending' ORDER BY created_at DESC";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order();
                
                order.setId(rs.getInt("id"));
                order.setOrderNumber(rs.getString("order_number"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setAmountPayment(rs.getDouble("amount_payment"));
                order.setStatus(OrderStatus.fromString(rs.getString("status")));
                order.setNotes(rs.getString("notes"));
                order.setReceipt(rs.getString("receipt"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdatedAt(rs.getTimestamp("updated_at"));

                orderList.add(order);
            }

        } catch (SQLException e) {
            System.err.println("Error saat mengambil data order: " + e.getMessage());
            e.printStackTrace();
        }

        return orderList;
    }
    
    
    public List<Order> getAllSuccessOrders() {
        List<Order> orderList = new ArrayList<>();
        
        String sql = "SELECT * FROM orders WHERE status = 'success' ORDER BY created_at DESC";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order();
                
                order.setId(rs.getInt("id"));
                order.setOrderNumber(rs.getString("order_number"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setAmountPayment(rs.getDouble("amount_payment"));
                order.setStatus(OrderStatus.fromString(rs.getString("status")));
                order.setNotes(rs.getString("notes"));
                order.setReceipt(rs.getString("receipt"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdatedAt(rs.getTimestamp("updated_at"));

                orderList.add(order);
            }

        } catch (SQLException e) {
            System.err.println("Error saat mengambil data order: " + e.getMessage());
            e.printStackTrace();
        }

        return orderList;
    }

    public boolean updateStatusById(Integer order_id) {
        //validasi
        String sql = "SELECT * FROM order_items WHERE order_id = ? AND is_done = ? ORDER BY created_at DESC";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, order_id);
            pstmt.setBoolean(2, false);

            ResultSet rs = pstmt.executeQuery();
            boolean dataDitemukan = false;
            while (rs.next()) {
                dataDitemukan = true;
            }
            
            if (!dataDitemukan) {
                System.out.println("bisa ditandai selesai");
                String sql1 = "UPDATE orders SET status = ? where id = ?";
                try (Connection conn1 = dbConnection.getConnection();
                    PreparedStatement pstmt1 = conn1.prepareStatement(sql1)) {
                    
                    pstmt1.setString(1, "success");
                    pstmt1.setInt(2, order_id);
                    
                    int barisTerpengaruh = pstmt1.executeUpdate();
            
                    if (barisTerpengaruh == 1) {
                        System.out.println("Update berhasil untuk order ID: " + order_id);
                        return true;
                    } else {
                        System.out.println("Update gagal. order ID: " + order_id + " tidak ditemukan.");
                        return false;
                    }
                }
            } else {
                System.out.println("Tidak bisa ditandai selesai");
                return false;
            }
  
        } catch(SQLException e) {
            System.err.println("Error saat update status order: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } 

}