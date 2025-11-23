package kasirui;

import amodels.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.Image;

public class listPesanan extends javax.swing.JPanel {
    
    private Product product;
    private Kasir1 parent;
    private int jumlah = 1;

    public listPesanan(Product product, Kasir1 parent) {
        this.product = product;
        this.parent = parent;
        initComponents();
        this.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        this.setAlignmentY(java.awt.Component.TOP_ALIGNMENT);
        if (this.product != null) {
            jLabel1.setText(this.product.getName());
            jLabel2.setText("Rp " + this.product.getPrice());
            
            if (product.getFoto() != null && !product.getFoto().isEmpty()) {
                ImageIcon icon = new ImageIcon(product.getFoto());
                Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);
                jLabel3.setIcon(icon);
            }
            
            jLabelJumlah.setText(String.valueOf(jumlah)); 


            try {
            } catch (Exception e) {}
            parent.updateSubtotal(product.getPrice(), 1);

            btnPlus.addActionListener((java.awt.event.ActionEvent evt) -> {
                jumlah++;
                jLabelJumlah.setText(String.valueOf(jumlah)); 
                
                parent.updateSubtotal(product.getPrice(), 1);
            });

            btnMinus.addActionListener((java.awt.event.ActionEvent evt) -> {
                if (jumlah > 0) { 
                    jumlah--; 
                    jLabelJumlah.setText(String.valueOf(jumlah)); 
                    
                    parent.updateSubtotal(-product.getPrice(), -1);
                    
                    if (jumlah == 0) {
                        parent.hapusItemDariKeranjang(listPesanan.this);
                    }
                }
            });
        }
        if (this.product != null) {
        }
    }

    public Product getProduct() {
        return this.product;
    }

    public void tambahJumlah() {
        jumlah++; 
        jLabelJumlah.setText(String.valueOf(jumlah)); 
        parent.updateSubtotal(product.getPrice(), 1); 
    }

    listPesanan(Product p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    int getJumlah(){
        return this.jumlah;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnMinus = new javax.swing.JButton();
        jLabelJumlah = new javax.swing.JLabel();
        btnPlus = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(234, 90));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("jLabel1");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("jLabel2");

        btnMinus.setText("-");
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        jLabelJumlah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJumlah.setText("jLabel4");

        btnPlus.setText("+");
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMinus)
                        .addGap(3, 3, 3)
                        .addComponent(jLabelJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnPlus))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMinus)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnPlus)
                                .addComponent(jLabelJumlah))))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPlusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnPlus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelJumlah;
    // End of variables declaration//GEN-END:variables

}
