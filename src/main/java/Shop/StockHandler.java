package Shop;

import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Connection;

/**
 * Created by Jack on 16/11/2015.
 */
public class StockHandler implements Route {

    ProductDao productDao;

    public StockHandler(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String productRequest = request.params(":product");
        int productStock = productDao.getProduct(productRequest).getStock();

        return productRequest + ":" + productStock;
    }
}
