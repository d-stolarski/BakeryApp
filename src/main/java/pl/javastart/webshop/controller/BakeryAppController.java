package pl.javastart.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.javastart.webshop.database.ProductDatabase;
import pl.javastart.webshop.model.Product;

import java.util.List;

@Controller
public class BakeryAppController {

    private ProductDatabase database;
    private double rollSum = 0;
    private double breadSum = 0;

    @Autowired
    public BakeryAppController(ProductDatabase database) {
        this.database = database;
    }

    @RequestMapping("/add")
    public String addProduct(@RequestParam(value = "bulka", required = true) String productRoll,
                             @RequestParam(value = "bulkaCena", required = true) double rollPrice,
                             @RequestParam(value = "bulkaIlosc", required = false, defaultValue = "0") int rollAmount,
                             @RequestParam(value = "chleb", required = true) String productBread,
                             @RequestParam(value = "chlebCena", required = true) double breadPrice,
                             @RequestParam(value = "chlebIlosc", required = false, defaultValue = "0") int breadAmount) {

        Product roll = new Product(productRoll, rollPrice);
        Product bread = new Product(productBread, breadPrice);

        for (int i = 0; i < rollAmount; i++) {
            database.addProduct(roll);
            rollSum += 0.4;
        }
        for (int i = 0; i < breadAmount; i++) {
            database.addProduct(bread);
            breadSum += 3.2;
        }
        return "added.html";
    }

    @RequestMapping("/basket")
    @ResponseBody
    public String showBasket() {
        List<Product> productList = database.getProducts();
        return productList.toString();
    }

    @RequestMapping("/basket/sum")
    @ResponseBody
    public String showSum() {
        Double fullSum = breadSum + rollSum;
        return "Do zapłaty " + fullSum.toString() + "zł";
    }
}
