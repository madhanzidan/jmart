package com.zidanJmartKD.controller;

import com.zidanJmartKD.Algorithm;
import com.zidanJmartKD.Predicate;
import com.zidanJmartKD.dbjson.JsonTable;
import com.zidanJmartKD.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

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
    public default List<T> getPage (@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "2") int pageSize){
        return Algorithm.paginate(getJsonTable().iterator(), page, pageSize, (index->true));
    }

}
