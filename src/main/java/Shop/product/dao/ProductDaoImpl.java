package Shop.product.dao;

import Shop.product.Product;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    private final Connection connection;

    @Inject
    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getProduct(String productId) {

        List<Product> list = new ArrayList<>();

        try {

            String query = "select * from shop.product where product_name like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + productId + "%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(result.getString("product_name"), result.getInt("stock"), result.getInt("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public boolean doesProductExists(String productId) {
        boolean productExists = false;
        try {

            String query = "select count(*) from shop.product where product_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int productCount = result.getInt(1);
                productExists = productCount > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productExists;
    }

    public void addProduct(Product product) {

        String query = "insert into shop.product(product_name, stock, price) values(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getStock());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.execute();
            connection.commit();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void deleteProduct(String productId) {
        try {

            String query = "delete from shop.product where product_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productId);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
