package Shop.handlers;

import Shop.product.Product;
import Shop.product.ProductDao;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class GetProductHandler implements Route {

    private final Gson gson;
    private final ProductDao productDao;

    public GetProductHandler(ProductDao productDao) {
        gson = new Gson();
        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String productRequest = request.params(":product");
        List<Product> products = productDao.getProduct(productRequest);

        return gson.toJson(products);

    }
}
