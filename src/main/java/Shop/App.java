package Shop;

import static spark.Spark.*;

import Shop.config.HandlerConfig;
import Shop.config.ShopConfig;
import Shop.handlers.AddProductHandler;
import Shop.handlers.PriceHandler;
import Shop.handlers.GetProductHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by jack on 28/10/15.
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfig.class, HandlerConfig.class);
        GetProductHandler getProductHandler = context.getBean(GetProductHandler.class);
        AddProductHandler addProductHandler = context.getBean(AddProductHandler.class);

        staticFileLocation("/webapp");

        get("/get/product/:product", getProductHandler);

        post("/add/:product/:stock/:price", addProductHandler);
    }

}
