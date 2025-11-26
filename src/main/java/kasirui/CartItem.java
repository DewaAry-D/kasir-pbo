package kasirui;
import amodels.Product;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    
    // Hitung total harga per item (Harga x Jumlah)
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    
    public Integer getId() {
        return product.getId();
    }
}