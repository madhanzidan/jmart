package zidanJmartKD;

public class Coupon extends Recognizable
{
    public final int code;
    public final double cut;
    public final double minimum;
    public final String name;
    public final Type type;
    private boolean used;
    
    public Coupon (String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }
    
    public double apply (Treasury priceTag)
    {
        used = true;
        if (type == Type.DISCOUNT)
            return priceTag.getAdjustedPrice(priceTag.price, priceTag.discount) - (priceTag.getAdjustedPrice(priceTag.price, priceTag.discount) * cut / 100); 
        else if (type == Type.REBATE)
            return priceTag.getAdjustedPrice(priceTag.price, priceTag.discount) - cut;
        else
            return 0;
    }
    
    public boolean canApply (Treasury priceTag)
    {
        if (priceTag.getAdjustedPrice(priceTag.price, priceTag.discount) >= minimum && used == false)
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
