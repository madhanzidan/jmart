package com.zidanJmartKD.controller;

import com.zidanJmartKD.*;
import com.zidanJmartKD.dbjson.JsonAutowired;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RequestMapping("/payment")
class PaymentController implements BasicGetController<Payment>{
    public static long DELIVERED_LIMIT_MS = 100;
    public static long ON_DELIVERY_LIMIT_MS = 100;
    public static long ON_PROGRESS_LIMIT_MS = 100;
    public static long WAITING_CONF_LIMIT_MS = 100;

    @JsonAutowired(filepath = "C:/Kuliah Semester 5/Java/jmart/file.json", value = Payment.class)
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    @PostMapping("/create")
    Payment create (@RequestParam int buyerId, @RequestParam int productId, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan)
    {
        Account acc = Algorithm.<Account>find(AccountController.accountTable, account -> account.id ==buyerId);
        Product prod = Algorithm.<Product>find(ProductController.productTable, product -> product.id == productId);
        Payment pay = Algorithm.<Payment>find(getJsonTable(), payment -> payment.buyerId == buyerId && payment.productId == productId);

        if (Algorithm.<Account>exists(AccountController.accountTable, acc) && Algorithm.<Product>exists(ProductController.productTable, prod)
            && pay.getTotalPay(prod) <= acc.balance && pay.shipment.cost == 0 && pay.shipment.receipt == null)
        {
            acc.balance -= pay.getTotalPay(prod);
            pay.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Menunggu konfirmasi"));
            getJsonTable().add(pay);
            poolThread.add(pay);
            return pay;
        }
        return null;
    }

    @PostMapping("/create")
    boolean accept (@RequestParam int id){
        Payment pay = Algorithm.<Payment>find(getJsonTable(), payment -> payment.id == id);
        if (pay.history.get(pay.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION)
        {
            pay.history.add(new Payment.Record(Invoice.Status.ON_PROGRESS, "Dalam proses"));
            return true;
        }
        return false;
    }

    @PostMapping("/create")
    boolean cancel (@RequestParam int id){
        Payment pay = Algorithm.<Payment>find(getJsonTable(), payment -> payment.id == id);
        if (pay.history.get(pay.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION)
        {
            pay.history.add(new Payment.Record(Invoice.Status.CANCELLED, "Dibatalkan"));
            return true;
        }
        return false;
    }

    @PostMapping("/create")
    boolean submit (@RequestParam int id, @RequestParam String receipt){
        Payment pay = Algorithm.<Payment>find(getJsonTable(), payment -> payment.id == id);
        if (pay.history.get(pay.history.size()-1).status == Invoice.Status.ON_PROGRESS && !pay.shipment.receipt.isBlank())
        {
            pay.shipment.receipt = receipt;
            pay.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "Dalam pengiriman"));
            return true;
        }
        return false;
    }

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    private static boolean timekeeper (@RequestParam Payment payment){
        boolean endSignal = true;
        while (!endSignal) {
            Date currentDate = new Date();
            int finalIndex = payment.history.size() - 1;
            long elapsedTime = currentDate.getTime() - payment.history.get(finalIndex).date.getTime();

            if(payment.history.get(finalIndex).status == Invoice.Status.WAITING_CONFIRMATION && (elapsedTime > WAITING_CONF_LIMIT_MS))
            {
                payment.history.remove(finalIndex);
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Fail Process"));
            }
            else if(payment.history.get(finalIndex).status == Invoice.Status.ON_PROGRESS && (elapsedTime > ON_PROGRESS_LIMIT_MS))
            {
                payment.history.remove(finalIndex);
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Fail Process"));
            }
            else if(payment.history.get(finalIndex).status == Invoice.Status.ON_DELIVERY && (elapsedTime > ON_DELIVERY_LIMIT_MS))
            {
                payment.history.remove(finalIndex);
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Sent"));
            }
            else if(payment.history.get(finalIndex).status == Invoice.Status.ON_DELIVERY && (elapsedTime > DELIVERED_LIMIT_MS))
            {
                payment.history.remove(finalIndex);
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Delivered"));
            }

            endSignal = true;
            for(Payment.Record index: payment.history)
            {
                if (index.status != Invoice.Status.FINISHED || index.status != Invoice.Status.FAILED)
                    endSignal = false;
            }
        }
        return endSignal;
    }

}
