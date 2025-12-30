package kasirui;

import amodels.Product;
import javax.swing.SwingWorker;
import java.net.URL;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.logging.Level;

public class listProduct extends javax.swing.JPanel {
private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Kasir1.class.getName());

    private final Product product;
    
    private SwingWorker<ImageIcon, Void> imageLoader;
    private Kasir1 parent;
    
    public listProduct(Product product, Kasir1 parent) {
        this.product = product;
        this.parent = parent;
        initComponents();
        
        jLabel1.setText(product.getName());
        jLabel2.setText("Rp" + product.getPrice());
         String imagePath = product.getFoto();

        if (imagePath != null && !imagePath.isEmpty()) {
            lblGambar.setText("Loading...");
            loadImage(imagePath); // proses gambar secara asynchronous
        } else {
            lblGambar.setIcon(null);
            lblGambar.setText("No Image");
        }
        setMaximumSize(getPreferredSize());
        
        btnAdd.addActionListener(e -> parent.tambahKePanelKanan(product));
    }
    private void loadImage(final String path) {
        
        if (imageLoader != null && !imageLoader.isDone()) {
            imageLoader.cancel(true);
        }

        imageLoader = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                Image img = null;
                
                if (path.startsWith("http://") || path.startsWith("https://")) {
                    try {
                        URL url = new URL(path);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                        connection.setConnectTimeout(5000);
                        connection.setReadTimeout(5000);
                        connection.connect();
                        
                        if (connection.getResponseCode() == 200) {
                            try (java.io.InputStream input = connection.getInputStream()) {
                                img = ImageIO.read(input);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Gagal download URL: " + e.getMessage());
                    }
                }
                
                else {
                    try {
                        File f = new File(path.trim().replace("\\\\", "\\"));
                        if (f.exists()) {
                            img = ImageIO.read(f);
                        } else {
                            System.err.println("File lokal tidak ditemukan: " + path);
                        }
                    } catch (Exception e) {
                        System.err.println("Gagal baca file lokal: " + e.getMessage());
                    }
                }
                
                if (img != null) {
                    int width = lblGambar.getWidth();
                    int height = lblGambar.getHeight();
                    if (width == 0) width = 100;
                    if (height == 0) height = 60;
                    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImg);
                }
                
                return null;
            }

            @Override
            protected void done() {
                try {
                    ImageIcon finalIcon = get();
                    if (finalIcon != null) {
                        lblGambar.setIcon(finalIcon);
                        lblGambar.setText("");
                    } else {
                        lblGambar.setIcon(null);
                        lblGambar.setText("Fail Load");
                    }
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Gagal memuat gambar: " + path, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        lblGambar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(192, 193));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("jLabel2");

        btnAdd.setBackground(new java.awt.Color(255, 153, 0));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblGambar;
    // End of variables declaration//GEN-END:variables
}
