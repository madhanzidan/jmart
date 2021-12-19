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

    /**
     * Inisialisasi seluruh informasi untuk coupon
     * @param name
     * @param code
     * @param type
     * @param cut
     * @param minimum
     */
    public Coupon (String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }

    /**
     * Mengaplikasikan coupon
     * @param price
     * @param discount
     * @return price yang telah diadjust berdasarkan penggunaan coupon
     */
    public double apply (double price, double discount)
    {
        used = true;
        if (type == Type.DISCOUNT)
            return Treasury.getAdjustedPrice(price, discount) - (Treasury.getAdjustedPrice(price, discount) * cut / 100); 
        else
            return Treasury.getAdjustedPrice(price, discount) - cut;
    }

    /**
     * Memastikan coupon dapat diapply
     * @param price
     * @param discount
     * @return penentuan apakah coupon dapat diapply atau tidak
     */
    public boolean canApply (double price, double discount)
    {
        if (Treasury.getAdjustedPrice(price, discount) >= minimum && used == false)
            return true;
        else
            return false;
    }

    /**
     * @return state pada coupon apabila telah digunakan
     */
    public boolean isUsed()
    {
        return used;
    }

    /**
     * Enumeration type of coupon
     */
    public static enum Type
    {
        DISCOUNT, REBATE;
    }
}
