package Shop.handlers;

import Shop.product.Product;
import Shop.product.ProductDao;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 16/11/2015.
 */
public class GetProductHandler implements Route {

    Gson gson = new Gson();
    ProductDao productDao;

    public GetProductHandler(ProductDao productDao) {

        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        List<Product> products = new ArrayList<>();
        String productRequest = request.params(":product");
        products = productDao.getProduct(productRequest);

        return gson.toJson(products);

    }
}
