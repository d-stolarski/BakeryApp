package pl.javastart.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.javastart.webshop.database.ProductDatabase;
import pl.javastart.webshop.model.Product;

@Controller
public class BakeryAppController {

    private ProductDatabase database;

    @Autowired
    public BakeryAppController(ProductDatabase database) {
        this.database = database;
    }

    @RequestMapping("/added")
    public String addProduct(@RequestParam (value = "bulka", required = true)String productRoll,
                            @RequestParam (value = "bulkaIlosc", required = false, defaultValue = "0")int rollAmount,
                            @RequestParam (value = "chleb", required = true)String productBread,
                            @RequestParam (value = "chlebIlosc", required = false, defaultValue = "0")int breadAmount){

        Product roll = new Product(productRoll, rollAmount);
        Product bread = new Product(productBread, breadAmount);

        if(rollAmount != 0){
            database.addProduct(roll);
        }
        if(breadAmount != 0){
            database.addProduct(bread);
        }
        return "added.html";
    }
}
