package ownerui;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;


public class owner22 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(owner22.class.getName());

    DbConnection db;
    Connection conn;

    public owner22() {
        initComponents();
        
        db = new DbConnection();
        try {
            conn = db.getConnection();
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Gagal terhubung ke database!", e);
        }
        
        loadJumlahProduk();
        loadTotalTransaksi();
        loadTotalPendapatan();
        loadRiwayatTransaksi();
        pengaturanTabel();
    }
    
    private void pengaturanTabel() {
        javax.swing.table.DefaultTableCellRenderer toolTip = new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null) {
                    setToolTipText(value.toString());
                }
                return this;
            }
        };
    
        // rata tengah header
        ((javax.swing.table.DefaultTableCellRenderer) tbl_riwayat.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        // rata tengah value
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        javax.swing.table.TableColumnModel columnModel = tbl_riwayat.getColumnModel();

        // Kolom No
        columnModel.getColumn(0).setPreferredWidth(40); 
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(0).setMinWidth(40); 
        columnModel.getColumn(0).setCellRenderer(centerRenderer);

        // Kolom Waktu Transaksi
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);

        // Kolom Order Number
        columnModel.getColumn(2).setPreferredWidth(140);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);

        // Kolom Nama Produk
        columnModel.getColumn(3).setPreferredWidth(250); 
        columnModel.getColumn(3).setMinWidth(200); 
        columnModel.getColumn(3).setCellRenderer(toolTip);

        // Kolom Jumlah
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);

        // Kolom Dibayar
        columnModel.getColumn(5).setPreferredWidth(150);
        columnModel.getColumn(5).setCellRenderer(toolTip);
    }
    
    private void loadJumlahProduk() {
        String sql = "SELECT COUNT(*) AS total_products FROM products";
    
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalProduk = rs.getInt("total_products");
                lbl_produk.setText(String.valueOf(totalProduk));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Gagal load jumlah produk!", e);
        }
    }
    
    private void loadTotalTransaksi() {
        String sql = "SELECT COUNT(*) AS total_transaksi FROM orders";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalTransaksi = rs.getInt("total_transaksi");
                lbl_transaksi.setText(String.valueOf(totalTransaksi));
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Gagal load total transaksi!", e);
        }
    }
    
    private void loadTotalPendapatan() {
        String sql = "SELECT SUM(total_price) AS pendapatan FROM orders WHERE status = 'success'";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double pendapatan = rs.getDouble("pendapatan");

                NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
                lbl_pendapatan.setText(rupiah.format(pendapatan));
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Gagal load total Pendapatan!", e);
        }
    }

    private void loadRiwayatTransaksi() {
        String sql = 
            "SELECT " +
            "o.created_at AS waktu_transaksi, " +
            "o.order_number, " +
            "GROUP_CONCAT(CONCAT(p.name, ' (', oi.quantity, ')') SEPARATOR ', ') AS produk_list, " +
            "SUM(oi.quantity) AS total_quantity, " +
            "o.amount_payment AS dibayar " +
            "FROM orders o " +
            "JOIN order_items oi ON o.id = oi.order_id " +
            "JOIN products p ON oi.product_id = p.id " +
            "GROUP BY o.id, o.order_number, o.created_at, o.amount_payment " +
            "ORDER BY o.created_at DESC " +
            "LIMIT 10";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tbl_riwayat.getModel();
            model.setRowCount(0);

            int no = 1;

            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            while (rs.next()) {
                String waktu = rs.getString("waktu_transaksi");
                String orderNumber = rs.getString("order_number");
                String produkList = rs.getString("produk_list");
                int qty = rs.getInt("total_quantity");
                double bayar = rs.getDouble("dibayar");

                String formattedBayar = formatter.format(bayar).replace("Rp", "Rp ");

                model.addRow(new Object[]{
                    no++,
                    waktu,
                    orderNumber,
                    produkList,
                    qty,
                    formattedBayar
                });
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Gagal load riwayat transaksi!", e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_produk = new javax.swing.JButton();
        btn_transaksi = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_transaksi = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_produk = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_pendapatan = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_riwayat = new javax.swing.JTable();

        jButton5.setBackground(new java.awt.Color(255, 153, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Transaksi");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(720, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(250, 165, 51));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 58));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP 240 G8\\OneDrive\\Documents\\NetBeansProjects\\kasir-pbo\\src\\main\\java\\image\\logo gacogan.png")); // NOI18N

        btn_produk.setBackground(new java.awt.Color(250, 165, 51));
        btn_produk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_produk.setForeground(new java.awt.Color(255, 255, 255));
        btn_produk.setText("Produk");
        btn_produk.setBorder(null);
        btn_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_produkActionPerformed(evt);
            }
        });

        btn_transaksi.setBackground(new java.awt.Color(250, 165, 51));
        btn_transaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        btn_transaksi.setText("Transaksi");
        btn_transaksi.setBorder(null);
        btn_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btn_produk, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_produk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(248, 248, 248));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setMaximumSize(new java.awt.Dimension(228, 85));
        jPanel7.setMinimumSize(new java.awt.Dimension(228, 85));
        jPanel7.setPreferredSize(new java.awt.Dimension(228, 85));

        jPanel3.setBackground(new java.awt.Color(0, 0, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(52, 61));
        jPanel3.setMinimumSize(new java.awt.Dimension(52, 61));
        jPanel3.setPreferredSize(new java.awt.Dimension(52, 61));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP 240 G8\\OneDrive\\Documents\\NetBeansProjects\\kasir-pbo\\src\\main\\java\\image\\shopping-cart_.png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setBackground(new java.awt.Color(0, 0, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Total Transaksi");

        lbl_transaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_transaksi.setText("117");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(lbl_transaksi))
                .addGap(0, 31, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_transaksi)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(248, 248, 248));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setMaximumSize(new java.awt.Dimension(228, 85));
        jPanel9.setMinimumSize(new java.awt.Dimension(228, 85));
        jPanel9.setPreferredSize(new java.awt.Dimension(228, 85));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel4.setMaximumSize(new java.awt.Dimension(52, 61));
        jPanel4.setMinimumSize(new java.awt.Dimension(52, 61));
        jPanel4.setPreferredSize(new java.awt.Dimension(52, 61));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP 240 G8\\OneDrive\\Documents\\NetBeansProjects\\kasir-pbo\\src\\main\\java\\image\\codesandbox_.png")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setBackground(new java.awt.Color(255, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Jumlah Produk");

        lbl_produk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_produk.setText("10");
        lbl_produk.setToolTipText("");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbl_produk))
                .addGap(0, 33, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_produk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(248, 248, 248));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setMaximumSize(new java.awt.Dimension(228, 85));
        jPanel11.setMinimumSize(new java.awt.Dimension(228, 85));
        jPanel11.setPreferredSize(new java.awt.Dimension(228, 85));

        jPanel5.setBackground(new java.awt.Color(204, 0, 204));
        jPanel5.setMaximumSize(new java.awt.Dimension(52, 61));
        jPanel5.setMinimumSize(new java.awt.Dimension(52, 61));
        jPanel5.setPreferredSize(new java.awt.Dimension(52, 61));

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP 240 G8\\OneDrive\\Documents\\NetBeansProjects\\kasir-pbo\\src\\main\\java\\image\\credit-card_.png")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setBackground(new java.awt.Color(255, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 204));
        jLabel7.setText("Total Pendapatan");

        lbl_pendapatan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_pendapatan.setText("Rp 25.000.000");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lbl_pendapatan))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pendapatan)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DASHBOARD UTAMA");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("10 TRANSAKSI TERBARU");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tbl_riwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "2025-10-30 12:05:10", "TR-1001", "Mie Gato lvl1", "2", "30.000"},
                {"2", "2025-10-30 12:05:10", "TR-1002", "Es Teh Manis", "2", "16.000"},
                {"3", "2025-10-30 12:15:20", "TR-1003", "Ayam Geprek", "1", "20.000"},
                {"4", "2025-10-30 12:40:05", "TR-1004", "Nasi Goreng Spesial", "1", "22.000"},
                {"5", "2025-10-30 12:40:05", "TR-1005", "Es Teh Manis", "1", "8.000"},
                {"6", "2025-10-30 13:10:30", "TR-1006", "Kopi Susu Gula Aren", "1", "18.000"},
                {"7", "2025-10-30 13:10:30", "TR-1007", "Keripik Singkong Balado", "2", "24.000"},
                {"8", "2025-10-30 14:05:00", "TR-1008", "Mie Gacogan", "3", "42.000"},
                {"9", "2025-10-30 14:05:00", "TR-1009", "Es Teh Manis", "3", "24.000"},
                {"10", "2025-10-30 14:30:15", "TR-1010", "Mie Gato lvl1", "1", "15.000"}
            },
            new String [] {
                "No", "Waktu Transaksi", "Order Number", "Nama Produk", "Jumlah", "Dibayar (Rp)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_riwayat.setGridColor(new java.awt.Color(255, 204, 102));
        tbl_riwayat.setMaximumSize(new java.awt.Dimension(450, 300));
        tbl_riwayat.setMinimumSize(new java.awt.Dimension(450, 300));
        tbl_riwayat.setRowHeight(30);
        tbl_riwayat.setSelectionBackground(new java.awt.Color(255, 204, 51));
        tbl_riwayat.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbl_riwayat.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_riwayat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Riwayat riwayat = new Riwayat();
        riwayat.setVisible(true);
        this.dispose();           // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_produkActionPerformed
        OwnerMenu2 menu = new OwnerMenu2();
        menu.setVisible(true);
        this.dispose();          // TODO add your handling code here:
    }//GEN-LAST:event_btn_produkActionPerformed

    private void btn_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transaksiActionPerformed
        Riwayat riwayat = new Riwayat();
        riwayat.setVisible(true);
        this.dispose();  
    }//GEN-LAST:event_btn_transaksiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Gagal mengatur Look and Feel Nimbus", ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new owner22().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_produk;
    private javax.swing.JButton btn_transaksi;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_pendapatan;
    private javax.swing.JLabel lbl_produk;
    private javax.swing.JLabel lbl_transaksi;
    private javax.swing.JTable tbl_riwayat;
    // End of variables declaration//GEN-END:variables
}
