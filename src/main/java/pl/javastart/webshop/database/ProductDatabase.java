package pl.javastart.webshop.database;

import org.springframework.stereotype.Component;
import pl.javastart.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDatabase {

    private List<Product> products = new ArrayList<>();


    public void addProduct(Product product){
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "ProductDatabase{" +
                "products=" + products +
                '}';
    }
}
