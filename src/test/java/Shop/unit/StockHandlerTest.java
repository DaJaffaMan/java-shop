package Shop.unit;

import Shop.config.HandlerConfig;
import Shop.config.ShopConfig;
import Shop.handlers.StockHandler;
import Shop.product.Product;
import Shop.product.ProductDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spark.Request;
import spark.Response;

import static org.junit.Assert.*;

/**
 * Created by Jack on 16/11/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class StockHandlerTest {

    @Mock
    private Request request;
    @Mock
    private Response response;

    ApplicationContext context = new AnnotationConfigApplicationContext(HandlerConfig.class, ShopConfig.class);
    StockHandler stockHandler = context.getBean(StockHandler.class);

    @Before
    public void setup() {
        ProductDao productDao = context.getBean(ProductDao.class);
        productDao.addProduct(new Product("banana", 1, 1.00));
        productDao.addProduct(new Product("apple", 0, 1.00));
    }

    @Test
    public void testGetItemWithStock() throws Exception {
        when(request.params(":product")).thenReturn("banana");
        String responseGiven = stockHandler.handle(request, response).toString();

        assertEquals("banana:1", responseGiven);
    }

    @Test
    public void testGetItemWithoutStock() throws Exception {
        when(request.params(":product")).thenReturn("apple");
        String responseGiven = stockHandler.handle(request, response).toString();

        assertEquals("apple:0", responseGiven);
    }
}