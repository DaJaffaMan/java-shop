package Shop.unit;

import Shop.config.HandlerConfig;
import Shop.config.ShopConfig;
import Shop.handlers.DeleteProductHandler;
import Shop.handlers.GetProductHandler;
import Shop.product.Product;
import Shop.product.ProductDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spark.Request;
import spark.Response;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetStockHandlerTest {

    @Mock
    private Request request;
    @Mock
    private Response response;

    ApplicationContext context;
    GetProductHandler getProductHandler;
    DeleteProductHandler deleteProductHandler;
    Connection connection;
    ProductDao productDao;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(HandlerConfig.class, ShopConfig.class);
        getProductHandler = context.getBean(GetProductHandler.class);
        connection = context.getBean(Connection.class);
        deleteProductHandler = context.getBean(DeleteProductHandler.class);

        productDao = context.getBean(ProductDao.class);
        productDao.addProduct(new Product("testproduct", 1, 1.00));
        productDao.addProduct(new Product("tstproduct", 0, 1.00));
    }

    @Test
    public void testGetItemWithStock() throws Exception {
        when(request.params(":product")).thenReturn("testproduct");
        String responseGiven = getProductHandler.handle(request, response).toString();

        assertEquals("[{\"productName\":\"testproduct\",\"stock\":1,\"price\":1.0}]", responseGiven);
    }

    @Test
    public void testGetItemWithoutStock() throws Exception {
        when(request.params(":product")).thenReturn("tstproduct");
        String responseGiven = getProductHandler.handle(request, response).toString();

        assertEquals("[{\"productName\":\"tstproduct\",\"stock\":0,\"price\":1.0}]", responseGiven);
    }

    @After
    public void teardown() throws Exception {
        connection.createStatement().execute("DROP SCHEMA shop");
    }
}