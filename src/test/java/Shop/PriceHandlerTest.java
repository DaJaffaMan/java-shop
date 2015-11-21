//package Shop;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.mockito.Mockito.*;
//
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import spark.Request;
//import spark.Response;
//
//import static org.junit.Assert.*;
//
///**
// * Created by Jack on 16/11/2015.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class PriceHandlerTest {
//
//    @Mock
//    private Request request;
//    @Mock
//    private Response response;
//
//    ProductDao productDao = new ProductDao();
//
//    @Before
//    public void setup() {
//        productDao.addProduct("banana", new Product(1, 1.00));
//    }
//
//    @Test
//    public void testGetItemPrice() throws Exception {
//        when(request.params(":product")).thenReturn("banana");
//
//        PriceHandler priceHandler = new PriceHandler(productDao);
//        String responseGiven = priceHandler.handle(request, response).toString();
//
//        assertEquals("banana:£1.00", responseGiven);
//    }
//}