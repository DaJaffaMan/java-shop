package Shop.product;

/**
 * Created by Jack on 14/11/2015.
 */
public class Product {

    String productName;
    int stock;
    double price;

    public Product(final String productName, final int stock, final double price) {

        this.productName = productName;
        this.stock = stock;
        this.price = price;

    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }
}
