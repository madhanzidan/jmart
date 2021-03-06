package com.zidanJmartKD.controller;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

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
    /**
     * Set time limit of every transaction state
     */
    public static long DELIVERED_LIMIT_MS = 100;
    public static long ON_DELIVERY_LIMIT_MS = 100;
    public static long ON_PROGRESS_LIMIT_MS = 100;
    public static long WAITING_CONF_LIMIT_MS = 100;

    /**
     * Store the payment information on the Payment.json
     */
    @JsonAutowired(filepath = "C:\\Kuliah Semester 5\\Java\\jmart\\Payment.json", value = Payment.class)
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<>(PaymentController::timekeeper);

    /**
     * Create payment event of product
     * @param buyerId
     * @param productId
     * @param productCount
     * @param shipmentAddress
     * @param shipmentPlan
     * @return payment information
     */
    @PostMapping("create")
    Payment create (@RequestParam int buyerId, @RequestParam int productId,@RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan)
    {
        Payment pay = new Payment(buyerId, productId, productCount, new Shipment(shipmentAddress, 0, shipmentPlan, null));
        Account acc = Algorithm.<Account>find(AccountController.accountTable, account -> account.id == buyerId);
        Product prod = Algorithm.<Product>find(ProductController.productTable, product -> product.id == productId);
        if (Algorithm.<Account>exists(AccountController.accountTable, acc)
                && Algorithm.<Product>exists(ProductController.productTable, prod)
                && pay.getTotalPay(prod) <= acc.balance
                && pay.shipment.cost == 0
                && pay.shipment.receipt == null) {
            acc.balance -= pay.getTotalPay(prod);
            pay.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Waiting for confirmation"));
            getJsonTable().add(pay);
            poolThread.add(pay);
            return pay;
        }
        return null;

    }

    /**
     * Create condition when payment accepted by store
     * @param id
     * @return paymen history and payment state
     */
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

    /**
     * Create condition when payment canceled by store
     * @param id
     * @return payment history
     */
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

    /**
     * Create condition when payment submitted by store
     * @param id
     * @param receipt
     * @return payment history
     */
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

    /**
     * @return the table information of payment
     */
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    /**
     * Create time keeper to set the state of payment process
     * @param payment
     * @return the end of process
     */
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
