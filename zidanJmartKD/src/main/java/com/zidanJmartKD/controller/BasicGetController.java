package com.zidanJmartKD.controller;

import com.zidanJmartKD.Algorithm;
import com.zidanJmartKD.Predicate;
import com.zidanJmartKD.dbjson.JsonTable;
import com.zidanJmartKD.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface BasicGetController<T extends Serializable>{
    /*
    @GetMapping("/{id}")
    public default <T> getById (@RequestMapping int id)
    {
        Predicate<T> pred = index -> index.id == id;
        return Algorithm.find(getJsonTable().iterator(), pred);
    }

    public abstract <T> JsonTable<T> getJsonTable();

    @GetMapping("/page")
    public default <T> List<T> getPage (int page, int pageSize)
    {
        return Algorithm.paginate(getJsonTable().iterator(), page, pageSize, null);
    }*/
    @GetMapping("/id")
    public default T getById(@PathVariable int id){
       // return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
        return null;
    }

    @GetMapping("/page")
    public default List<T> getPage (@PathVariable int page, @PathVariable int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e->true);
    }
    public abstract JsonTable<T> getJsonTable();
}
