package Shop.handlers;

import Shop.product.Product;
import Shop.product.ProductDao;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddProductHandler implements Route {

    private final ProductDao productDao;

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
