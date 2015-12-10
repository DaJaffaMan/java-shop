package Shop.config;

import Shop.handlers.AddProductHandler;
import Shop.handlers.GetProductHandler;
import Shop.product.ProductDao;
import org.springframework.context.annotation.Bean;

/**
 * Created by Jack on 22/11/2015.
 */
public class HandlerConfig {

    @Bean
    public GetProductHandler stockHandler(ProductDao productDao) throws ClassNotFoundException {

        return new GetProductHandler(productDao);
    }

    @Bean
    public AddProductHandler productHandler(ProductDao productDao) throws ClassNotFoundException {

        return new AddProductHandler(productDao);
    }
}
