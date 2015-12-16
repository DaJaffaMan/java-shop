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
import org.junit.After;
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
    ApplicationContext context = new AnnotationConfigApplicationContext(HandlerConfig.class, ShopConfig.class);

    @Before
    public void setup() {
        ProductDao productDao = context.getBean(ProductDao.class);
        productDao.addProduct(new Product("testproduct", 1, 1));
    }

    @Test
    public void testGetProductHTTPResponse() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:4567/get/product/testproduct");

        try {
            HttpResponse response = new DefaultHttpClient().execute(request);

            assertEquals(200, response.getStatusLine().getStatusCode());
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testThrow404HttpResponseOnNonExistantProductUrl() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:4567/notgetting/product/testproduct");

        try {
            HttpResponse response = new DefaultHttpClient().execute(request);

            assertEquals(404, response.getStatusLine().getStatusCode());
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddProductHTTPResponse() throws IOException {
        HttpUriRequest request = new HttpPost("http://localhost:4567/add/tstproduct/1/1");

        try {
            HttpResponse response = new DefaultHttpClient().execute(request);

            httpEntity = response.getEntity();

            assertEquals(200, response.getStatusLine().getStatusCode());
            assertEquals("tstproduct added", EntityUtils.toString(httpEntity));
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

    @After
    public void teardown() throws Exception {
        ProductDao productDao = context.getBean(ProductDao.class);
        productDao.deleteProduct("testproduct");
        productDao.deleteProduct("tstproduct");
    }
}
