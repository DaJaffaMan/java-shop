package Shop.product;

import org.json.JSONObject;

import java.io.StringWriter;

/**
 * Created by Jack on 14/11/2015.
 */
public class Product {

    JSONObject jsonObject = new JSONObject();
    StringWriter writer = new StringWriter();
    String productName;
    int stock;
    double price;

    public Product(final String productName, final int stock, final double price) {
        this.jsonObject.put("item", productName);
        this.jsonObject.put("stock", stock);
        this.jsonObject.put("price", price);

        this.productName = productName;
        this.stock = stock;
        this.price = price;

        jsonObject.write(writer);

    }

    public int getStock() {
        String jsonText = jsonObject.get("stock").toString();
        int stock = Integer.parseInt(jsonText);
        return stock;
    }

    public double getPrice() {
        String jsonText = jsonObject.get("price").toString();
        double price = Double.parseDouble(jsonText);
        return price;
    }

    public String getProductName() {
        String jsonText = jsonObject.get("item").toString();
        return jsonText;
    }
}
