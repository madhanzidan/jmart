package zidanJmartKD;

public class Product extends Recognizable implements FileParser
{
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
        "\nrating: " + rating.getAverage() +
        "\nstoreId: " + storeId;
    }
    
    @Override //FileParser
    public boolean read (String content){
        return false;
    }
}
