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
    
    public static String  getCustomer()
    {
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after)
    {
        float potonganHarga;
        
        if (before > after)
        {
            potonganHarga = before - after;
            potonganHarga = (potonganHarga * 100) / before;
        }
        else
        {
            potonganHarga = 0;
        }
        return potonganHarga;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        float endDiscountedPrice  = price - (price * discountPercentage / 100);
        if (discountPercentage > 100.0f)
        {
            return 0;
        }
        else
        {
            return (int)endDiscountedPrice;
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        float originalPrice = discountedPrice / (discountPercentage / 100);
        
        return (int)originalPrice;
    }
    
    public float getCommisionMultiplier()
    {
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price)
    {
        return 0;
    }
    
    public static int  getAdminFee(int price)
    {
        return 0;
    }
    
}
