package Shop.product;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.sql.*;

/**
 * Created by Jack on 14/11/2015.
 */
@Component
public class ProductDao {

    private final Connection connection;

    @Inject
    public ProductDao(Connection connection) {
        this.connection = connection;
    }


    public Product getProduct(String productId) {

        Product product;

        try {

            String query = "select * from shop.product where product_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                product = new Product(result.getString("product_name"), result.getInt("stock"), result.getInt("price"));

                return product;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void addProduct(Product product) {

        String query = "insert into product(product_name, stock, price) values(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getStock());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.execute();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}