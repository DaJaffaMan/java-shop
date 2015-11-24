package Shop.integration;

import Shop.config.HandlerConfig;
import Shop.config.ShopConfig;
import Shop.product.Product;
import Shop.product.ProductDao;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.xml.ws.http.HTTPException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jack on 23/11/2015.
 */
public class RestClientTest {

    HttpEntity httpEntity;

    @Before
    public void setup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(HandlerConfig.class, ShopConfig.class);
        ProductDao productDao = context.getBean(ProductDao.class);
        productDao.addProduct(new Product("banana", 1, 1));
    }

    @Test
    public void testGetProductStockHTTPResponse() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:4567/get/stock/banana");

        try {
            HttpResponse response = new DefaultHttpClient().execute(request);

            httpEntity = response.getEntity();

            assertEquals(200, response.getStatusLine().getStatusCode());
            assertEquals("banana:1", EntityUtils.toString(httpEntity));
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetProductPriceHTTPResponse() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:4567/get/price/banana");

        try {
            HttpResponse response = new DefaultHttpClient().execute(request);

            httpEntity = response.getEntity();

            assertEquals(200, response.getStatusLine().getStatusCode());
            assertEquals("banana:£1.00", EntityUtils.toString(httpEntity));
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddProductHTTPResponse() throws IOException {
        HttpUriRequest request = new HttpPost("http://localhost:4567/add/banana/1/1");

        try {
            HttpResponse response = new DefaultHttpClient().execute(request);

            httpEntity = response.getEntity();

            assertEquals(200, response.getStatusLine().getStatusCode());
            assertEquals("banana added", EntityUtils.toString(httpEntity));
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

}
