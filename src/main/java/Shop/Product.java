package Shop;

/**
 * Created by Jack on 14/11/2015.
 */
public class Product {

    private final String productName;
    private final int stock;
    private final double price;

    public Product(final String productName, final int stock, final double price) {
        this.stock = stock;
        this.price = price;
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() { return productName; }
}
