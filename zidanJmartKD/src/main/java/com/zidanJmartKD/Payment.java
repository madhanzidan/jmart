package com.zidanJmartKD;

import java.util.Date;
import java.util.ArrayList;

public class Payment extends Invoice
{
	public ArrayList<Record> history = new ArrayList<>();
	public int productCount;
    public Shipment shipment;
    
    public Payment(int buyerId, int productId, int productCount, Shipment shipment)
    {
      super(buyerId, productId);
      this.productCount = productCount;
      this.shipment = shipment;
    }
    
    @Override
    public double getTotalPay(Product product)
    {
        return product.price - (product.price*product.discount);
    }
    
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
