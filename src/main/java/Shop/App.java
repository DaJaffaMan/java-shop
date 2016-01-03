package Shop;

import Shop.config.ShopConfig;
import Shop.handlers.AddProductHandler;
import Shop.handlers.GetProductHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.*;

public class App {

    private static AnnotationConfigApplicationContext context;

    public static void main(String[] args) {

        context = new AnnotationConfigApplicationContext(ShopConfig.class);
        final GetProductHandler getProductHandler = context.getBean(GetProductHandler.class);
        final AddProductHandler addProductHandler = context.getBean(AddProductHandler.class);

        final String appPort = System.getProperty("app.port", "5000");
        port(Integer.valueOf(appPort));

        staticFileLocation("/webapp");

        get("/get/product/:product", getProductHandler);

        post("/add/:product/:stock/:price", addProductHandler);
    }

    public static void shutdown() {
        stop();
        context.close();
    }

}