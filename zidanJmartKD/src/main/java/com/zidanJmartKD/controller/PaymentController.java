package com.zidanJmartKD.controller;

import com.zidanJmartKD.Payment;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;

public class PaymentController implements BasicGetController<Payment>{
    public static long DELIVERED_LIMIT_MS = 100;
    public static long ON_DELIVERY_LIMIT_MS = 100;
    public static long ON_PROGRESS_LIMIT_MS = 100;
    public static long WAITING_CONF_LIMIT_MS = 100;

    @PostMapping("/create")
    boolean accept (int id){
        return true;
    }

    @PostMapping("/create")
    boolean cancel (int id){
        return true;
    }

    @PostMapping("/create")
    Payment create (int buyerId, int productId, String shipmentAddress, byte shipmentPlan)
    {
        return  null;
    }

    @Override
    public JsonTable<Payment> getJsonTable() {
        return null;
    }

    @PostMapping("/create")
    boolean submit (int id, String receipt){
        return true;
    }

    private static boolean timekeeper (Payment payment){
        return false;
    }

}
