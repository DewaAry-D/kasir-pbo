package ownerui;

import amodels.Product;
import javax.swing.SwingWorker;
import java.net.URL;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import ownerui.OwnerMenu2;
import controller.ProductController;
import database.DbConnection;
import java.util.logging.Level;

public class CardProduct extends javax.swing.JPanel {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OwnerMenu2.class.getName());

    private Product product;
    
    private SwingWorker<ImageIcon, Void> imageLoader;

    public CardProduct(Product product) {
        this.product = product;
        
        initComponents();
        loadDataKeForm();
        
        jScrollPane1.setWheelScrollingEnabled(true);
    }

    private void loadDataKeForm() {
        lblNamaProduk.setText(product.getName());
        lblHarga.setText("Rp " + String.format("%,.0f", product.getPrice()));
        txtDeskripsi.setText(product.getDescription());

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
                    logger.log(Level.SEVERE, "Gagal memuat gambar dari URL: " + path, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(157, 260));
        setMinimumSize(new java.awt.Dimension(157, 260));
        setPreferredSize(new java.awt.Dimension(157, 260));

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
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblGambar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGambar.setText("lblGambar");
        lblGambar.setMaximumSize(new java.awt.Dimension(121, 72));
        lblGambar.setMinimumSize(new java.awt.Dimension(121, 72));
        lblGambar.setPreferredSize(new java.awt.Dimension(121, 72));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDeskripsi.setEditable(false);
        txtDeskripsi.setColumns(20);
        txtDeskripsi.setLineWrap(true);
        txtDeskripsi.setRows(5);
        txtDeskripsi.setTabSize(5);
        txtDeskripsi.setText("Deskripsi\n");
        txtDeskripsi.setWrapStyleWord(true);
        txtDeskripsi.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtDeskripsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtDeskripsi.setFocusable(false);
        txtDeskripsi.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtDeskripsi.setMaximumSize(new java.awt.Dimension(220, 80));
        txtDeskripsi.setMinimumSize(new java.awt.Dimension(220, 80));
        txtDeskripsi.setOpaque(false);
        jScrollPane1.setViewportView(txtDeskripsi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNamaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addComponent(lblHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        OwnerMenu2 menu = (OwnerMenu2) SwingUtilities.getWindowAncestor(this);
    
        if (menu != null) {
            menu.bukaTabEdit(this.product);
        } else {
            System.out.println("Error: Tidak bisa menemukan jendela OwnerMenu2");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // pop up konfirmasi
        int jawaban = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin menghapus produk: " + product.getName() + "?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        
        if (jawaban == JOptionPane.YES_OPTION) {
            try {
                DbConnection db = new DbConnection();
                ProductController controller = new ProductController(db);
                
                boolean sukses = controller.hapusProduk(product.getId());
                
                if (sukses) {
                    JOptionPane.showMessageDialog(this, "Produk berhasil dihapus!");
                    
                    OwnerMenu2 menu = (OwnerMenu2) SwingUtilities.getWindowAncestor(this);
                    
                    if (menu != null) {
                        menu.loadDataProduk(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus produk.");
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Gagal menghapus produk dengan ID: " + product.getId(), e);
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGambar;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblNamaProduk;
    private javax.swing.JTextArea txtDeskripsi;
    // End of variables declaration//GEN-END:variables
}
