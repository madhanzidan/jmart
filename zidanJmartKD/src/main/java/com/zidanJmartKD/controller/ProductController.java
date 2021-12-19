package com.zidanJmartKD.controller;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

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
    /**
     * Store the product information on Product.json
     */
    @JsonAutowired(value = Product.class, filepath = "C:\\Kuliah Semester 5\\Java\\jmart\\Product.json")
    public static JsonTable<Product> productTable;

    /**
     * Create new product of store
     * @param accountId
     * @param name
     * @param weight
     * @param conditionUsed
     * @param price
     * @param discount
     * @param category
     * @param shipmentPlans
     * @return new product
     */
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

    /**
     * @return table consists of product information
     */
    public JsonTable<Product> getJsonTable ()
    {
        return productTable;
    }

    /**
     * Get the product of a store
     * @param id
     * @param page
     * @param pageSize
     * @return product data of a store on page
     */
    @GetMapping("/{id}/store")
    List<Product> getProductByStore (@PathVariable int id, @RequestParam int page, @RequestParam int pageSize)
    {
        Predicate<Product> pred = product -> true;
        List<Product> list = new ArrayList<>();

        for(Product product : getJsonTable()) {
            list.add(product);
        }
        return Algorithm.<Product>paginate(list, page, pageSize, pred);
    }

    /**
     * Get product that already filtered
     * @param page
     * @param pageSize
     * @param accountId
     * @param search
     * @param minPrice
     * @param maxPrice
     * @param category
     * @return filtered product data on page
     */
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
