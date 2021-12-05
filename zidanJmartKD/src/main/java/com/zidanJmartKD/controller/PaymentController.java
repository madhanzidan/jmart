package com.zidanJmartKD.controller;

import com.zidanJmartKD.*;
import com.zidanJmartKD.dbjson.JsonAutowired;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.zidanJmartKD.Algorithm.find;
import static com.zidanJmartKD.controller.AccountController.accountTable;
import static com.zidanJmartKD.controller.ProductController.productTable;

@RestController
@RequestMapping("/payment")
class PaymentController implements BasicGetController<Payment>{
    public static long DELIVERED_LIMIT_MS = 100;
    public static long ON_DELIVERY_LIMIT_MS = 100;
    public static long ON_PROGRESS_LIMIT_MS = 100;
    public static long WAITING_CONF_LIMIT_MS = 100;

    @JsonAutowired(filepath = "C:\\Kuliah Semester 5\\Java\\jmart\\Payment.json", value = Payment.class)
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    @PostMapping("create")
    Payment create (@RequestParam int buyerId, @RequestParam int productId,@RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan)
    {
        Predicate<Account> findAcc = accFound -> accFound.id == buyerId;
        Predicate<Product> findProd = prodFound -> prodFound.id == productId;

        Product prod = find(productTable, findProd);
        Account acc = find(accountTable, findAcc);
        Payment pay = Algorithm.<Payment>find(paymentTable, (Predicate<Payment>) pred -> prod.id == buyerId && acc.id == buyerId);
        Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
        pay.shipment = shipment;

        if (Algorithm.exists(accountTable, findAcc) &&
                Algorithm.exists(productTable, findProd) &&
                acc.balance >= prod.price * productCount)
        {
            acc.balance -= pay.getTotalPay(prod) * productCount;
            pay.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Menunggu konfirmasi"));
            getJsonTable().add(pay);
            poolThread.add(pay);
        }
        else
            return null;
        return pay;
    }

    @PostMapping("{id}/accept")
    boolean accept (@RequestParam int id){
        Predicate<Payment> findPay = payFound -> payFound.id == id;
        Algorithm.find(paymentTable, findPay);
        Payment pay = Algorithm.find(paymentTable, findPay);

        if (Algorithm.exists(paymentTable, findPay) &&
            pay.history.get(pay.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION)
        {
            pay.history.add(new Payment.Record(Invoice.Status.ON_PROGRESS, "Dalam proses"));
            return true;
        }
        else
            return false;
    }

    @PostMapping("{id}/cancel")
    boolean cancel (@PathVariable int id){
        Predicate<Payment> findPay = payFound -> payFound.id == id;
        Algorithm.find(paymentTable, findPay);
        Payment pay = Algorithm.find(paymentTable, findPay);

        if (Algorithm.exists(paymentTable, findPay) &&
                pay.history.get(pay.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION)
        {
            pay.history.add(new Payment.Record(Invoice.Status.CANCELLED, "Dibatalkan"));
            return true;
        }
        else
            return false;
    }

    @PostMapping("{id}/submit")
    boolean submit (@PathVariable int id, @RequestParam String receipt)
    {
        Predicate<Payment> findPay = payFound -> payFound.id == id;
        Algorithm.find(paymentTable, findPay);
        Payment pay = Algorithm.find(paymentTable, findPay);

        if (Algorithm.exists(paymentTable, findPay) &&
                pay.history.get(pay.history.size()-1).status == Invoice.Status.ON_PROGRESS
                && !pay.shipment.receipt.isBlank())
        {
            pay.shipment.receipt = receipt;
            pay.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "Dalam proses pengiriman"));
            return true;
        }
        else
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
