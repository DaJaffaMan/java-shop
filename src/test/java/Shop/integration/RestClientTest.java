package Shop.integration;

import Shop.App;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static spark.Spark.stop;

public class RestClientTest {

    CloseableHttpClient httpClient;
    HttpResponse httpResponse;

    @Before
    public void setup() {
        httpClient = HttpClientBuilder.create().build();
        App.main(null);
    }

    @Test
    public void testGetProductHTTPResponse() throws IOException {

        HttpUriRequest addProductRequest = new HttpPost("http://localhost:5000/add/testproduct/1/1");
        HttpUriRequest getProductRequest = new HttpGet("http://localhost:5000/get/product/testproduct");

        try {
            httpClient.execute(addProductRequest);
            httpResponse = httpClient.execute(getProductRequest);

            assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testThrow404HttpResponseOnNonExistantUrl() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:5000/notgetting/product/testproduct");

        try {
            HttpResponse response = httpClient.execute(request);

            assertEquals(404, response.getStatusLine().getStatusCode());
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddProductHTTPResponse() throws IOException {
        HttpUriRequest request = new HttpPost("http://localhost:5000/add/testproduct/1/1");

        try {

            HttpResponse response = httpClient.execute(request);
            HttpEntity httpEntity = response.getEntity();

            assertEquals(200, response.getStatusLine().getStatusCode());
            assertEquals("testproduct added", EntityUtils.toString(httpEntity));
        } catch (HTTPException e) {
            System.out.println(e.getMessage());
        }
    }

    @After
    public void teardown() throws Exception {
        httpClient.close();
        stop();
        App.shutdown();
    }
}
