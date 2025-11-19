package ownerui;

import amodels.Product;
import javax.swing.SwingWorker;
import java.net.URL;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class CardProduct extends javax.swing.JPanel {

    private Product product;
    
    private SwingWorker<ImageIcon, Void> imageLoader;

    public CardProduct(Product product) {
        this.product = product;
        initComponents();
        loadDataKeForm();
    }

    private void loadDataKeForm() {
        lblNamaProduk.setText(product.getName());
        lblHarga.setText("Rp " + String.format("%,.0f", product.getPrice()));

        String imagePath = product.getFoto();

        if (imagePath != null && !imagePath.isEmpty()) {
            lblGambar.setText("Loading...");
            loadImage(imagePath); // proses gambar secara asynchronous
        } else {
            lblGambar.setIcon(null);
            lblGambar.setText("No Image");
        }
    }

    private void loadImage(final String path) {
        
        if (imageLoader != null && !imageLoader.isDone()) {
            imageLoader.cancel(true);
        }

        imageLoader = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                Image img = null;
                URL url = new URL(path);
                img = ImageIO.read(url);

                if (img != null) {
                    int width = lblGambar.getWidth();
                    int height = lblGambar.getHeight();
                    if (width == 0) width = 121;
                    if (height == 0) height = 72;
                    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImg);
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    ImageIcon finalIcon = get(); // Ambil hasil dari doInBackground()
                    if (finalIcon != null) {
                        lblGambar.setIcon(finalIcon);
                        lblGambar.setText("");
                    } else {
                        lblGambar.setIcon(null);
                        lblGambar.setText("Fail Load");
                    }
                } catch (Exception ex) {
                    System.err.println("Gagal menampilkan gambar: " + ex.getMessage());
                    lblGambar.setIcon(null);
                    lblGambar.setText("Error");
                }
            }
        };
        imageLoader.execute();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNamaProduk = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblGambar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(157, 178));
        setMinimumSize(new java.awt.Dimension(157, 178));
        setPreferredSize(new java.awt.Dimension(157, 178));

        lblNamaProduk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNamaProduk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaProduk.setText("Mie gato lvl1");
        lblNamaProduk.setMaximumSize(new java.awt.Dimension(125, 20));
        lblNamaProduk.setMinimumSize(new java.awt.Dimension(125, 20));
        lblNamaProduk.setPreferredSize(new java.awt.Dimension(125, 20));

        lblHarga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHarga.setText("Rp. 500000");
        lblHarga.setMaximumSize(new java.awt.Dimension(125, 20));
        lblHarga.setMinimumSize(new java.awt.Dimension(125, 20));
        lblHarga.setPreferredSize(new java.awt.Dimension(125, 20));

        btnEdit.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP 240 G8\\OneDrive\\Documents\\NetBeansProjects\\kasir-pbo\\src\\main\\java\\image\\edit.png")); // NOI18N
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP 240 G8\\OneDrive\\Documents\\NetBeansProjects\\kasir-pbo\\src\\main\\java\\image\\delete.png")); // NOI18N
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblGambar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGambar.setText("lblGambar");
        lblGambar.setMaximumSize(new java.awt.Dimension(121, 72));
        lblGambar.setMinimumSize(new java.awt.Dimension(121, 72));
        lblGambar.setPreferredSize(new java.awt.Dimension(121, 72));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNamaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addComponent(lblHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel lblGambar;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblNamaProduk;
    // End of variables declaration//GEN-END:variables
}
