package com.zidanJmartKD.controller;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import com.zidanJmartKD.Algorithm;
import com.zidanJmartKD.Predicate;
import com.zidanJmartKD.dbjson.JsonTable;
import com.zidanJmartKD.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * Membuat interface untuk diimplementasikan di setiap controller
 * @param <T>
 */
@RestController
public interface BasicGetController<T extends Serializable>{
    @GetMapping("/{id}")
    public default T getById (@PathVariable int id)
    {
        Predicate<T> pred = index -> index.id == id;
        return Algorithm.find(getJsonTable().iterator(), pred);
    }

    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/page")
    public default List<T> getPage (@RequestParam int page, @RequestParam int pageSize){
        Predicate<T> pred = element -> true;
        List<T> list = Algorithm.collect(getJsonTable(), pred);
        return Algorithm.<T>paginate(list, page, pageSize, pred);
    }

}
