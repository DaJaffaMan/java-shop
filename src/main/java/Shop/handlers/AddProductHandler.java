package Shop.handlers;

import Shop.product.Product;
import Shop.product.ProductDao;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Jack on 17/11/2015.
 */
public class AddProductHandler implements Route {

    ProductDao productDao;

    public AddProductHandler(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if (Double.parseDouble(request.params(":price")) > 0.00) {
            productDao.addProduct(new Product(request.params(":product"), Integer.parseInt(request.params(":stock")), Double.parseDouble(request.params(":price"))));
            return request.params(":product") + " added";
        } else {
            return "no price";
        }
    }
}
