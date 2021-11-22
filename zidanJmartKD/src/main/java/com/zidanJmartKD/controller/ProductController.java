package com.zidanJmartKD.controller;

import com.zidanJmartKD.*;
import com.zidanJmartKD.dbjson.JsonAutowired;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    @JsonAutowired(filepath = "C:/Kuliah Semester 5/Java/jmart/json", value = Product.class)
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
        if(Algorithm.exists(AccountController.accountTable.iterator(), predId) && Algorithm.exists(AccountController.accountTable.iterator(), predStore))
        {
            Product product = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
            productTable.add(product);
            return product;
        }
        return null;
    }

    public JsonTable<Product> getJsonTable ()
    {
        return productTable;
    }

    @GetMapping("/{id}/store")
    List<Product> getProductByStore (@PathVariable int id, @RequestParam int page, @RequestParam int pageSize)
    {
        Predicate<Product> pred = product -> product.accountId == id;
        return Algorithm.paginate(this.getJsonTable().iterator(), page, pageSize, pred);
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (@RequestParam int page, @RequestParam int pageSize, @RequestParam int accountId, @RequestParam String search, @RequestParam int minPrice, @RequestParam int maxPrice, @RequestParam ProductCategory category)
    {
        return null;
    }






}
