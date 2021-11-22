package com.zidanJmartKD.controller;

import com.zidanJmartKD.ObjectPoolThread;
import com.zidanJmartKD.Payment;
import com.zidanJmartKD.dbjson.JsonAutowired;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/payment")
class PaymentController implements BasicGetController<Payment>{
    public static long DELIVERED_LIMIT_MS = 100;
    public static long ON_DELIVERY_LIMIT_MS = 100;
    public static long ON_PROGRESS_LIMIT_MS = 100;
    public static long WAITING_CONF_LIMIT_MS = 100;

    @JsonAutowired(filepath = "C:/Kuliah Semester 5/Java/jmart/json", value = Payment.class)
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    @PostMapping("/create")
    boolean accept (int id){
        return false;
    }

    @PostMapping("/create")
    boolean cancel (int id){
        return false;
    }

    @PostMapping("/create")
    Payment create (int buyerId, int productId, String shipmentAddress, byte shipmentPlan)
    {
        return  null;
    }

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/create")
    boolean submit (int id, String receipt){
        return false;
    }

    private static boolean timekeeper (Payment payment){
        return false;
    }

}
