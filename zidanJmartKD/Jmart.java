package zidanJmartKD;

public class Jmart
{
    public static void main(String[] args)
    {
        
    }
    
    public static int getPromo()
    {
        return 0;
    }
    
    public static String getCustomer()
    {
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after)
    {
        int potonganHarga = before - after; 
        
        if (before > after)
        {
            potonganHarga = (potonganHarga * 100) / before;
            return (float)potonganHarga;
        }
        else
        {
            potonganHarga = 0;
        }
        return (float)potonganHarga;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        if (discountPercentage > 100.0f)
        {
            return 0;
        }
        else
        {
            return (int)(price - (price * discountPercentage / 100));
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        return (int)(discountedPrice / (discountPercentage / 100));
    }
    
    public static float getCommisionMultiplier()
    {
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price)
    {
       // return price + (int)(price * getCommisionMultiplier());
       return (int)(price + (price * getCommisionMultiplier()));
    }
    
    public static int getAdminFee(int price)
    {
        return (int)(price * getCommisionMultiplier());
    }
}
