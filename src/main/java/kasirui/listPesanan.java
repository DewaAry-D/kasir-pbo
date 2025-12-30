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

public class listPesanan extends javax.swing.JPanel {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Kasir1.class.getName());

    private final Product product;
    
    private SwingWorker<ImageIcon, Void> imageLoader;
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
            
            String imagePath = product.getFoto();

        if (imagePath != null && !imagePath.isEmpty()) {
            lblGambar.setText("Loading...");
            loadImage(imagePath); // proses gambar secara asynchronous
        } else {
            lblGambar.setIcon(null);
            lblGambar.setText("No Image");
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
                .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(lblGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel jLabelJumlah;
    private javax.swing.JLabel lblGambar;
    // End of variables declaration//GEN-END:variables

}
