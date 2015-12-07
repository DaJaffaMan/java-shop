package Shop.handlers;

import Shop.product.ProductDao;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.text.DecimalFormat;

/**
 * Created by Jack on 16/11/2015.
 */
public class PriceHandler implements Route {

    DecimalFormat df = new DecimalFormat("#.00");
    ProductDao productDao;

    public PriceHandler(ProductDao productDao) {

        this.productDao = productDao;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String productRequest = request.params(":product");
        double productPrice = productDao.getProduct(productRequest).getPrice();

        return productRequest + ":£" + df.format(productPrice);
    }

}
