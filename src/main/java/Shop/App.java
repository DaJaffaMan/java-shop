package Shop;

import static spark.Spark.*;

import java.sql.Connection;

import Shop.config.HandlerConfig;
import Shop.config.ShopConfig;
import Shop.handlers.PriceHandler;
import Shop.handlers.ProductHandler;
import Shop.handlers.StockHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by jack on 28/10/15.
 */
public class App {

    static Connection connection;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfig.class, HandlerConfig.class);
        StockHandler stockHandler = context.getBean(StockHandler.class);
        PriceHandler priceHandler = context.getBean(PriceHandler.class);
        ProductHandler productHandler = context.getBean(ProductHandler.class);

        get("/get/stock/:product", stockHandler);

        get("/get/price/:product", priceHandler);

        post("/add/:product/:stock/:price", productHandler);
    }

}
