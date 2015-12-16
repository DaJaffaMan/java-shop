package Shop.config;

import Shop.handlers.AddProductHandler;
import Shop.handlers.DeleteProductHandler;
import Shop.handlers.GetProductHandler;
import Shop.product.ProductDao;
import org.springframework.context.annotation.Bean;

/**
 * Created by Jack on 22/11/2015.
 */
public class HandlerConfig {

    @Bean
    public GetProductHandler getProductHandler(ProductDao productDao) throws ClassNotFoundException {

        return new GetProductHandler(productDao);
    }

    @Bean
    public AddProductHandler addProductHandler(ProductDao productDao) throws ClassNotFoundException {

        return new AddProductHandler(productDao);
    }

    @Bean
    public DeleteProductHandler deleteProductHandler(ProductDao productDao) throws ClassNotFoundException {

        return new DeleteProductHandler(productDao);
    }
}
