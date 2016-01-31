package Shop.handlers;

import Shop.product.dao.ProductDao;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;

@Component
public class DeleteProductHandler implements Route {

    private final ProductDao productDao;

    @Inject
    public DeleteProductHandler(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String productRequest = request.params(":product");
        if (productDao.doesProductExists(request.params(":product"))) {
            productDao.deleteProduct(productRequest);
            return request.params(":product") + " removed";
        } else {
            return "product does not exist";
        }
    }
}
