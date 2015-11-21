package Shop;

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
public class PriceHandlerTest {

    @Mock
    private Request request;
    @Mock
    private Response response;

    ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfig.class);
    ProductDao productDao = context.getBean(ProductDao.class);
    PriceHandler priceHandler = context.getBean(PriceHandler.class);


    @Before
    public void setup() {
        ProductDao productDao = context.getBean(ProductDao.class);
        productDao.addProduct(new Product("banana", 1, 1.00));
    }

    @Test
    public void testGetItemPrice() throws Exception {
        when(request.params(":product")).thenReturn("banana");
        String responseGiven = priceHandler.handle(request, response).toString();

        assertEquals("banana:£1.00", responseGiven);
    }
}