package kasirui;

import java.awt.Image;
import javax.swing.ImageIcon;

public class cardItemKeranjang extends javax.swing.JPanel {

    public cardItemKeranjang(CartItem item) {
        initComponents();
        
        // 1. Set Data ke Label
        lblNama.setText(item.getProduct().getName());
        lblHarga.setText("Rp " + item.getProduct().getPrice());
        lblQty.setText("x" + item.getQuantity()); // Menampilkan x2, x1, dst
        
        // Menampilkan Total per item (misal: 15.000 x 2 = 30.000)
        lblTotal.setText("Total: Rp " + item.getTotalPrice());
        
        // 2. Set Gambar
        try {
            if (item.getProduct().getFoto() != null) {
               ImageIcon icon = new ImageIcon(item.getProduct().getFoto());
               Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH); // Sesuaikan ukuran
               lblGambar.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {}
        
        java.awt.Dimension cardSize = new java.awt.Dimension(230, 90); 
        
        this.setPreferredSize(cardSize);
        this.setMaximumSize(cardSize);
        this.setMinimumSize(cardSize);
    }
    
    // ... generated code ...

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGambar = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        lblQty = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(230, 90));
        setPreferredSize(new java.awt.Dimension(230, 90));

        lblGambar.setPreferredSize(new java.awt.Dimension(100, 60));

        lblNama.setText("jLabel2");

        lblHarga.setText("jLabel3");

        lblQty.setText("jLabel4");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotal.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lblQty, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNama)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHarga)
                            .addComponent(lblQty))
                        .addGap(9, 9, 9)
                        .addComponent(lblTotal))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblGambar;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblTotal;
    // End of variables declaration//GEN-END:variables
}
