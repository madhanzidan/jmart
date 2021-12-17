package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import com.zidanJmartKD.dbjson.Serializable;

public class Coupon extends Serializable
{
    public final int code;
    public final double cut;
    public final double minimum;
    public final String name;
    public final Type type;
    public boolean used;
    
    public Coupon (String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }
   
    public double apply (double price, double discount)
    {
        used = true;
        if (type == Type.DISCOUNT)
            return Treasury.getAdjustedPrice(price, discount) - (Treasury.getAdjustedPrice(price, discount) * cut / 100); 
        else
            return Treasury.getAdjustedPrice(price, discount) - cut;
    }
    
    public boolean canApply (double price, double discount)
    {
        if (Treasury.getAdjustedPrice(price, discount) >= minimum && used == false)
            return true;
        else
            return false;
    }
    
    public boolean isUsed()
    {
        return used;
    }
   
    public static enum Type
    {
        DISCOUNT, REBATE;
    }
}
