package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

public class Treasury
{
	public static final double BOTTOM_FEE = 1000.0;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public double discount;
    public double price;
    
    public static double getDiscountedPrice(double price, double discount)
    {
        if (discount > 100.0)
            discount = 100.0;
        if (discount == 100.0)
            return 0.0;
            
        return price - (price * discount / 100);
    }
    
    public static double getAdminFee(double price, double discount)
    {
       if (getDiscountedPrice(price, discount) <= BOTTOM_PRICE)
           return BOTTOM_FEE;
       else
           return COMMISSION_MULTIPLIER * getDiscountedPrice(price, discount);
    }
    
    public static double getAdjustedPrice(double price, double discount)
    {
        return getDiscountedPrice(price, discount) + getAdminFee(price, discount);
    }
}
