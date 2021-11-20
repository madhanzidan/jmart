package com.zidanJmartKD;
import com.zidanJmartKD.dbjson.Serializable;

import java.util.Date;


public abstract class Invoice extends Serializable
{
	public int buyerId;
	public int complaintId;
    public final Date date;
    public int productId;
    public Rating rating;
    
    
    
    protected Invoice (int buyerId, int productId)
    {
		this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        complaintId = -1;
        rating = Rating.NONE;
    }
    
    public abstract double getTotalPay(Product product);
    
    //Enum
    public static enum Status{
        CANCELLED, COMPLAINT, DELIVERED, FAILED, FINISHED, ON_DELIVERY, ON_PROGRESS, WAITING_CONFIRMATION;
    }
    public static enum Rating{
        BAD, GOOD, NEUTRAL, NONE;
    }
    
    public class Record
    {
    	public Date date;
    	public String message;
        public Status status;
    }
}
