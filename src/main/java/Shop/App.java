package Shop;

import static spark.Spark.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by jack on 28/10/15.
 */
public class App {

    static int bananaStock = 10;
    static int appleStock = 10;

    static int customerApples = 0;
    static int customerBananas = 0;

    static Map<Object, Object> basket = new HashMap<>();

    static Connection connection;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfig.class);
        // Get stock handler example
        StockHandler stockHandler = context.getBean(StockHandler.class);

        // TODO remove when using DI
        ProductDao productDao = new ProductDao(connection);

        // TODO register handlers using beans
        get("/get/stock/:product", new StockHandler(productDao));

        get("/get/price/:product", new PriceHandler(productDao));

        post("/add/:product/:stock/:price", new ProductHandler(productDao));
    }

}
