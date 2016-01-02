package Shop.product;

public class Product {

    private final String productName;
    private final int stock;
    private final double price;

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
