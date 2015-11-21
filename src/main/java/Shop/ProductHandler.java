package Shop;

import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Connection;

/**
 * Created by Jack on 17/11/2015.
 */
public class ProductHandler implements Route {

    ProductDao productDao;

    public ProductHandler(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if (Double.parseDouble(request.params(":price")) > 0.00) {
            productDao.addProduct(new Product(request.params(":product"),Integer.parseInt(request.params(":stock")), Double.parseDouble(request.params(":price"))));
            return request.params(":product") + " added";
        } else {
            return "no price";
        }
    }
}
