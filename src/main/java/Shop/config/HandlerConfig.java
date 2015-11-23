package Shop.config;

import Shop.handlers.PriceHandler;
import Shop.handlers.ProductHandler;
import Shop.handlers.StockHandler;
import Shop.product.ProductDao;

import org.springframework.context.annotation.Bean;

/**
 * Created by Jack on 22/11/2015.
 */
public class HandlerConfig {

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
