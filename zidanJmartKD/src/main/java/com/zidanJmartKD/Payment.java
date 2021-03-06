package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import java.util.Date;
import java.util.ArrayList;

public class Payment extends Invoice
{
	public ArrayList<Record> history = new ArrayList<>();
	public int productCount;
    public Shipment shipment;

    /**
     * Constructor payment untuk menginisialisasi id, productCount, dan shipment
     * @param buyerId
     * @param productId
     * @param productCount
     * @param shipment
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment)
    {
      super(buyerId, productId);
      this.productCount = productCount;
      this.shipment = shipment;
    }

    /**
     * Get the actual price of product purchase
     * @param product
     * @return
     */
    @Override
    public double getTotalPay(Product product)
    {
        return (product.price - (product.price*(product.discount/100)))*productCount;
    }

    /**
     * Set the data information of every purchase record
     */
    public static class Record
    {
    	public final Date date;
    	public String message;
    	public Status status;
    
    
	    public Record (Status status, String message)
	    {
	    	this.status = status;
	    	this.message = message;
	    	date = new Date();
	    }
    }
}
