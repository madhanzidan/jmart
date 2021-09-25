package zidanJmartKD;

public class Jmart
{
    
    public static void main(String[] args)
    {
        
    }
    
      public static Product createProduct()
    {
        /*
        Product smartphone = new Product("Smart Phone", 500, false, new PriceTag(8500000), ProductCategory.ELECTRONIC);
        return smartphone;
        */
       
       return null;
    }
    public static Coupon createCoupon()
    {
        Coupon flashSale = new Coupon("FLASH SALE", 777, Coupon.Type.DISCOUNT, 40, 100000);
        return flashSale;
    }
    
    public static ShipmentDuration createShipmentDuration()
    {
        ShipmentDuration domestic = new ShipmentDuration(ShipmentDuration.INSTANT, ShipmentDuration.NEXT_DAY); 
        return domestic;
    }
    
    public Product create()
    {
        /*
        Product weddingCake = new Product("Wedding Cake", 2000, false, new PriceTag(1000000), ProductCategory.WEDDING);
        return weddingCake;
        */
       return null;
    }
}