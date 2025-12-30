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

public class cardItemKeranjang extends javax.swing.JPanel {
private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(List_Keranjang2.class.getName());    
    private SwingWorker<ImageIcon, Void> imageLoader;
    public cardItemKeranjang(CartItem item) {
        initComponents();
        
        // 1. Set Data ke Label
        lblNama.setText(item.getProduct().getName());
        lblHarga.setText("Rp " + item.getProduct().getPrice());
        lblQty.setText("x" + item.getQuantity()); // Menampilkan x2, x1, dst
        
        // Menampilkan Total per item (misal: 15.000 x 2 = 30.000)
        lblTotal.setText("Total: Rp " + item.getTotalPrice());
        
       String imagePath = item.getProduct().getFoto();

        if (imagePath != null && !imagePath.isEmpty()) {
            lblGambar.setText("Loading...");
            loadImage(imagePath); // proses gambar secara asynchronous
        } else {
            lblGambar.setIcon(null);
            lblGambar.setText("No Image");
        }
        
        java.awt.Dimension cardSize = new java.awt.Dimension(230, 90); 
        
        this.setPreferredSize(cardSize);
        this.setMaximumSize(cardSize);
        this.setMinimumSize(cardSize);
    }
    
    // ... generated code ...
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
