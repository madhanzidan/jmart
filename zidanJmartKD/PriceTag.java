package zidanJmartKD;

public class PriceTag
{
    public double COMISSION_MULTIPLIER = 0.05;
    public double BOTTOM_PRICE = 20000.0;
    public double BOTTOM_FEE = 1000.0;
    public double discount;
    public double price;
    
    public PriceTag (double price)
    {
        this.price = price;
        discount = 0.0;
    }
    
    public PriceTag (double price, double discount)
    {
        this.price = price;
        this.discount = discount;
    }
    
    private double getDiscountedPrice()
    {
        if (discount > 100.0)
        {
            discount = 100.0;
        }
        if (discount == 100.0)
        {
            discount = 0.0;
        }
        
        return price - (price * discount / 100);
    }
    
    public double getAdminFee()
    {
       if (getDiscountedPrice() != BOTTOM_PRICE)
       {
           return BOTTOM_FEE;
       }
       else
       {
           return (95/100)*getDiscountedPrice();
       }
    }
    
    public double getAdjustedPrice()
    {
        return getDiscountedPrice() + getAdminFee();
    }
}
