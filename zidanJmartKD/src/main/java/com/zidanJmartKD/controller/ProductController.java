package com.zidanJmartKD.controller;

import com.zidanJmartKD.*;
import com.zidanJmartKD.dbjson.JsonAutowired;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.zidanJmartKD.controller.AccountController.accountTable;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    @JsonAutowired(value = Product.class, filepath = "C:\\Kuliah Semester 5\\Java\\jmart\\Product.json")
    public static JsonTable<Product> productTable;

    @PostMapping("/create")
    Product create (@RequestParam int accountId,
                    @RequestParam String name,
                    @RequestParam int weight,
                    @RequestParam boolean conditionUsed,
                    @RequestParam double price,
                    @RequestParam double discount,
                    @RequestParam ProductCategory category,
                    @RequestParam byte shipmentPlans)
    {
        Predicate<Account> predStore = account -> account.store!=null;
        Predicate<Account> predId = account -> account.id==accountId;
        if(Algorithm.exists(accountTable, predId) && Algorithm.exists(accountTable, predStore))
        {
            Product product = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
            productTable.add(product);
            return product;
        }
        else{
            return null;
        }
    }

    public JsonTable<Product> getJsonTable ()
    {
        return productTable;
    }

    @GetMapping("/{id}/store")
    List<Product> getProductByStore (@PathVariable int id, @RequestParam int page, @RequestParam int pageSize)
    {
        Predicate<Product> pred = product -> product.accountId == id;
        List<Product> list = new ArrayList<>();
        //return Algorithm.paginate(this.getJsonTable().iterator(), page, pageSize, pred);
        for(Product product : getJsonTable()) {
            list.add(product);
        }
        return Algorithm.<Product>paginate(list, page, pageSize, pred);
    }

    /*
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "2") int pageSize, @RequestParam int accountId, @RequestParam String search, @RequestParam int minPrice, @RequestParam int maxPrice, @RequestParam ProductCategory category)
    {
        return Algorithm.<Product>paginate(getJsonTable(), page, pageSize, (product -> product.accountId == accountId
                && product.name.toLowerCase().contains(search)
                && product.price >= minPrice && product.price <= maxPrice
                && product.category == category));
    }*/
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam int page, @RequestParam int pageSize, @RequestParam int accountId,
                                     @RequestParam String search, @RequestParam int minPrice, @RequestParam int maxPrice,
                                     @RequestParam ProductCategory category) {
        Predicate<Product> filter = filtered -> filtered.accountId == accountId
                && filtered.name.toLowerCase().contains((search.toLowerCase()))
                && filtered.price >= minPrice
                && filtered.price <= maxPrice
                && filtered.category.equals(category);

        List<Product> list = new ArrayList<>();
        for(Product product : getJsonTable()) {
            list.add(product);
        }
        return Algorithm.paginate(list, page, pageSize, filter);
    }

}
