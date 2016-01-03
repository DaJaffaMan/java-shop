package Shop.handlers;

import Shop.product.Product;
import Shop.product.ProductDao;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;

@Component
public class AddProductHandler implements Route {

    private final ProductDao productDao;

    @Inject
    public AddProductHandler(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if (Double.parseDouble(request.params(":price")) <= 0.00) {
            return "no price";
        } else if (productDao.doesProductExists(request.params(":product"))) {
            return "product already exists";
        } else {
            productDao.addProduct(new Product(request.params(":product"), Integer.parseInt(request.params(":stock")), Double.parseDouble(request.params(":price"))));
            return request.params(":product") + " added";
        }
    }
}
