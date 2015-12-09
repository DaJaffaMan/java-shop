package Shop.handlers;

import Shop.product.ProductDao;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jack on 16/11/2015.
 */
public class StockHandler implements Route {

    Gson gson = new Gson();
    ProductDao productDao;

    public StockHandler(ProductDao productDao) {

        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        Map map = new HashMap<>();

        String productRequest = request.params(":product");
        int productStock = productDao.getProduct(productRequest).getStock();

        map.put(productRequest, productStock);

        return gson.toJson(map);
    }
}
