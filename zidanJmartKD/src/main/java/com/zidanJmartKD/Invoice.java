package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import com.zidanJmartKD.dbjson.Serializable;

import java.util.Date;


public abstract class Invoice extends Serializable
{
	public int buyerId;
	public int complaintId;
    public final Date date;
    public int productId;
    public Rating rating;

    /**
     * Inisialisasi aspek-aspek informasi pada invoice
     * @param buyerId
     * @param productId
     */
    protected Invoice (int buyerId, int productId)
    {
		this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        complaintId = -1;
        rating = Rating.NONE;
    }

    /**
     * Abstract to get total pay
     * @param product
     * @return
     */
    public abstract double getTotalPay(Product product);

    /**
     * Enum of transaction status
     */
    public static enum Status{
        CANCELLED, COMPLAINT, DELIVERED, FAILED, FINISHED, ON_DELIVERY, ON_PROGRESS, WAITING_CONFIRMATION;
    }

    /**
     * Enum of product rating
     */
    public static enum Rating{
        BAD, GOOD, NEUTRAL, NONE;
    }

    /**
     * Record for invoice
     */
    public class Record
    {
    	public Date date;
    	public String message;
        public Status status;
    }
}
