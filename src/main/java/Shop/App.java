package Shop;

import Shop.config.HandlerConfig;
import Shop.config.ShopConfig;
import Shop.handlers.AddProductHandler;
import Shop.handlers.GetProductHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.*;

public class App {

    private static AnnotationConfigApplicationContext context;

    public static void main(String[] args) {

        String appPort = System.getProperty("app.port", "5000");

        context = new AnnotationConfigApplicationContext(ShopConfig.class, HandlerConfig.class);
        GetProductHandler getProductHandler = context.getBean(GetProductHandler.class);
        AddProductHandler addProductHandler = context.getBean(AddProductHandler.class);

        staticFileLocation("/webapp");

        port(Integer.valueOf(appPort));

        get("/get/product/:product", getProductHandler);

        post("/add/:product/:stock/:price", addProductHandler);
    }

    public static void shutdown() {
        stop();
        context.close();
    }

}