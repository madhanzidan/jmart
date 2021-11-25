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
    @JsonAutowired(filepath = "C:/Kuliah Semester 5/Java/jmart/file.json", value = Coupon.class)
    public static JsonTable<Coupon> couponTable;

    public JsonTable<Coupon> getJsonTable ()
    {
        return couponTable;
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed (@PathVariable int id)
    {
        return Algorithm.<Coupon>find(getJsonTable(), (coupon -> coupon.id == id)).used;
    }

    @GetMapping("/{id}/canApply")
    boolean canApply (@PathVariable int id, @RequestParam double price, @RequestParam double discount)
    {
        return Algorithm.<Coupon>find(getJsonTable(), (coupon -> coupon.id == id)).canApply(price, discount);
    }


    @GetMapping("/getAvailable")
    List<Coupon> getAvailable(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "2") int pageSize) {
        return Algorithm.paginate(couponTable.iterator(), page, pageSize, (coupon -> !coupon.used));
    }
}
