package zidanJmartKD;

public class Product extends Recognizable
{
   // private static int idCounter = 0;
    public int id;
    
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;
    public Shipment.MultiDuration multiDuration;
    
    public Product(int id, int storeId, String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category, Shipment.MultiDuration multiDuration)
    {
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.multiDuration = multiDuration;
    }
    
    public String toString(){
        return 
        "Name: "+ name +
        "\nWeight: " + weight +
        "\nconditionUsed: " + conditionUsed +
        "\npriceTag: " + priceTag +
        "\ncategory: " + category +
        "\nrating: " + rating +
        "\nstoreId: " + storeId;
    }
    
    public boolean read (String content){
        return false;
    }
    
    
    public Product(int id, Store store, String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category)
    {
        super(id);
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;        
    }
    
}
