package kasirui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Struk extends javax.swing.JFrame {

    // Penampung data
    List<CartItem> items;
    double total, bayar, kembalian;
    String namaCustomer;

    // CONSTRUCTOR KHUSUS (Menerima semua data transaksi)
    public Struk(List<CartItem> items, double total, double bayar, double kembalian, String nama) {
        initComponents();
        
        this.items = items;
        this.total = total;
        this.bayar = bayar;
        this.kembalian = kembalian;
        this.namaCustomer = nama;
        
        setupStruk();
    }

    private void setupStruk() {
        // 1. Buat ID Transaksi Otomatis (Format: TR + TahunBulanHari + JamMenit)
        SimpleDateFormat sdfId = new SimpleDateFormat("yyyyMMddHHmm");
        String idTransaksi = "TR" + sdfId.format(new Date());
        lblNoTransaksi.setText("#" + idTransaksi); // Pastikan ada label ini di design
        
        // 2. Set Tanggal Hari Ini
        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        lblTanggal.setText(sdfDate.format(new Date())); // Pastikan ada label ini
        
        // 3. Set Nama Customer
        lblNamaCustomer.setText(namaCustomer); // Pastikan ada label ini
        
        // 4. Tampilkan List Barang Belanjaan
        // Pastikan Anda punya JPanel kosong di tengah struk (misal: jPanelItems)
        jPanelItems.removeAll();
        jPanelItems.setLayout(new BoxLayout(jPanelItems, BoxLayout.Y_AXIS));
        
        for (CartItem item : items) {
        javax.swing.JPanel row = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        
        // --- LOGIKA BARU UNTUK GAP VERTIKAL ---
        // Tambahkan border kosong di bawah sebesar 6 pixel sebagai jarak
        row.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 6, 0)); 
        
        // Ubah tinggi dari 16 menjadi 22 (16 konten + 6 gap)
        java.awt.Dimension rowSize = new java.awt.Dimension(420, 22); 
        
        row.setPreferredSize(rowSize);
        row.setMaximumSize(rowSize);
        row.setMinimumSize(rowSize);
        row.setBackground(new java.awt.Color(255, 255, 255)); // Putih atau sesuai background
        
        // 2. Label Nama Produk [73, 16]
        javax.swing.JLabel lblNama = new javax.swing.JLabel(item.getProduct().getName());
        lblNama.setPreferredSize(new java.awt.Dimension(73, 16));
        lblNama.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // 3. Gap Horizontal (80)
        java.awt.Component gap1 = javax.swing.Box.createHorizontalStrut(80);
        
        // 4. Label Qty [6, 16]
        javax.swing.JLabel lblQty = new javax.swing.JLabel(String.valueOf(item.getQuantity())); 
        lblQty.setPreferredSize(new java.awt.Dimension(6, 30));
        lblQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQty.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // 5. Gap Horizontal (95)
        java.awt.Component gap2 = javax.swing.Box.createHorizontalStrut(95);
        
        // 6. Label Harga [54, 16]
        String hargaRapi = String.format("%.0f", item.getTotalPrice());
        javax.swing.JLabel lblHarga = new javax.swing.JLabel("Rp. " + hargaRapi);
        // Note: Saya lebarkan sedikit dari 54 ke 100 agar "Rp." muat dan tidak terpotong titik-titik
        lblHarga.setPreferredSize(new java.awt.Dimension(100, 16)); 
        lblHarga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHarga.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // --- SUSUN ---
        row.add(lblNama);
        row.add(gap1);
        row.add(lblQty);
        row.add(gap2);
        row.add(lblHarga);
        
        // --- MASUKKAN KE PANEL UTAMA ---
        jPanelItems.add(row);
        }
        
        // 5. Set Angka-angka Bawah
        lblSubTotal.setText("Rp " + total);
        lblBayar.setText("Rp " + bayar);
        lblKembalian.setText("Rp " + kembalian);
        
        jPanelItems.revalidate();
        jPanelItems.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblNoTransaksi = new javax.swing.JLabel();
        lblNamaCustomer = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanelItems = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblSubTotal = new javax.swing.JLabel();
        lblBayar = new javax.swing.JLabel();
        lblKembalian = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(455, 264));

        lblNoTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNoTransaksi.setText("#TR202508039239");

        lblNamaCustomer.setText(" Mande");

        lblTanggal.setText("Minggu, 28 November 2012");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Detail Transaksi");

        jLabel5.setText("Mie gato lvl 1");

        jLabel6.setText("1");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Rp. 30.000");

        javax.swing.GroupLayout jPanelItemsLayout = new javax.swing.GroupLayout(jPanelItems);
        jPanelItems.setLayout(jPanelItemsLayout);
        jPanelItemsLayout.setHorizontalGroup(
            jPanelItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelItemsLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelItemsLayout.setVerticalGroup(
            jPanelItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5)
            .addComponent(jLabel6)
            .addComponent(jLabel7)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("SubTotal :");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Pembayaran :");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Kembalian :");

        lblSubTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSubTotal.setText("Rp. 80.000");

        lblBayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBayar.setText("Rp. 100.000");

        lblKembalian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKembalian.setText("Rp. 20.000");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 108, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblSubTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblBayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblKembalian))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(lblNamaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNoTransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTanggal)
                    .addComponent(lblNamaCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(250, 165, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(250, 165, 51));
        jButton2.setText("Cetak Struk");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton2))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Kasir1 kasir = new Kasir1();
        kasir.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        javax.swing.JOptionPane.showMessageDialog(this, "Sedang mencetak struk ke printer...");
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelItems;
    private javax.swing.JLabel lblBayar;
    private javax.swing.JLabel lblKembalian;
    private javax.swing.JLabel lblNamaCustomer;
    private javax.swing.JLabel lblNoTransaksi;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblTanggal;
    // End of variables declaration//GEN-END:variables
}
