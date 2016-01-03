package Shop.unit;

import Shop.config.ShopConfig;
import Shop.handlers.AddProductHandler;
import Shop.handlers.DeleteProductHandler;
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
public class AddProductHandlerTest {

    @Mock
    private Request request;
    @Mock
    private Response response;

    ApplicationContext context;
    AddProductHandler addProductHandler;
    DeleteProductHandler deleteProductHandler;
    Connection connection;


    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(ShopConfig.class);
        addProductHandler = context.getBean(AddProductHandler.class);
        deleteProductHandler = context.getBean(DeleteProductHandler.class);
        connection = context.getBean(Connection.class);
    }

    @Test
    public void testAddItem() throws Exception {

        when(request.params(":product")).thenReturn("pickle");
        when(request.params(":stock")).thenReturn("1");
        when(request.params(":price")).thenReturn("1.00");

        String responseGiven = addProductHandler.handle(request, response).toString();

        assertEquals("pickle added", responseGiven);
    }

    @Test
    public void testAddExistingItem() throws Exception {

        when(request.params(":product")).thenReturn("pickle");
        when(request.params(":stock")).thenReturn("1");
        when(request.params(":price")).thenReturn("1.00");

        addProductHandler.handle(request, response);
        String responseGiven = addProductHandler.handle(request, response).toString();

        assertEquals("product already exists", responseGiven);
    }

    @Test
    public void testAddItemWithoutPriceShouldFail() throws Exception {

        when(request.params(":product")).thenReturn("pickle");
        when(request.params(":stock")).thenReturn("1");
        when(request.params(":price")).thenReturn("0.00");

        String responseGiven = addProductHandler.handle(request, response).toString();

        assertEquals("no price", responseGiven);
    }

    @After
    public void teardown() throws Exception {
        connection.createStatement().execute("DROP SCHEMA shop");
    }
}