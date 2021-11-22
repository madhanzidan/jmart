package com.zidanJmartKD.controller;

import com.zidanJmartKD.*;
import com.zidanJmartKD.dbjson.JsonAutowired;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {
    @JsonAutowired(filepath = "C:/Kuliah Semester 5/Java/jmart/json", value = Coupon.class)
    public static JsonTable<Coupon> couponTable;

    public JsonTable<Coupon> getJsonTable ()
    {
        return couponTable;
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed (@PathVariable int id)
    {
        for (Coupon coupon :couponTable)
        {
            if(coupon.id == id)
                return coupon.isUsed();
        }
        return false;
    }

    @GetMapping("/{id}/canApply")
    boolean canApply (@PathVariable int id, @RequestParam double price, @RequestParam double discount)
    {
        Predicate<Coupon> couponPredicate = applicable -> applicable.id == id;
        return Objects.requireNonNull(Algorithm.find(getJsonTable(), couponPredicate)).canApply(price, discount);
    }


    @GetMapping("/getAvalable")
    boolean getAvailable (int page, int pageSize)
    {
        return false;
    }




}
