package Shop.handlers;

import Shop.product.ProductDao;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Jack on 14/12/2015.
 */
public class DeleteProductHandler implements Route {

    ProductDao productDao;

    public DeleteProductHandler(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String productRequest = request.params(":product");
        if (productDao.doesProductExists(request.params(":product")) == true) {
            productDao.deleteProduct(productRequest);
            return request.params(":product") + " removed";
        } else {
            return "product does not exist";
        }
    }
}
