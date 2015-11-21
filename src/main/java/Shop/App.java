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
        StockHandler stockHandler = context.getBean(StockHandler.class);
        PriceHandler priceHandler = context.getBean(PriceHandler.class);
        ProductHandler productHandler = context.getBean(ProductHandler.class);

        get("/get/stock/:product", stockHandler);

        get("/get/price/:product", priceHandler);

        post("/add/:product/:stock/:price", productHandler);
    }

}
