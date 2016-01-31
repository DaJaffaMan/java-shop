package Shop.product.dao;

import Shop.product.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProduct(String productId);

    boolean doesProductExists(String productId);

    void addProduct(Product product);

    void deleteProduct(String productId);
}
