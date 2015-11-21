package Shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan
public class ShopConfig {

    @Bean
    public String jdbcUrl() {
        // Get host from -Ddb.host property or default to localhost
        return String.format("jdbc:mysql://%s/Shop", System.getProperty("db.host", "localhost"));
    }

    @Bean
    public Connection connection(String jdbcUrl) throws ClassNotFoundException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver");
        // Get user from -Ddb.user property or default to root
        // Get password from -Ddb.password property or default to password
        return DriverManager.getConnection(jdbcUrl, System.getProperty("db.user", "user"), System.getProperty("db.password", "password"));
    }

    @Bean
    public ProductDao productDao(Connection connection) {

        return new ProductDao(connection);
    }

    @Bean
    public StockHandler stockHandler(ProductDao productDao) throws ClassNotFoundException {

        return new StockHandler(productDao);
    }

    @Bean
    public PriceHandler priceHandler(ProductDao productDao) throws ClassNotFoundException {

        return new PriceHandler(productDao);
    }

    @Bean
    public ProductHandler productHandler(ProductDao productDao) throws ClassNotFoundException {

        return new ProductHandler(productDao);
    }
}
