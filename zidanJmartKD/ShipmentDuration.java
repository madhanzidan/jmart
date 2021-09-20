package zidanJmartKD;
public class ShipmentDuration
{
    public static final ShipmentDuration INSTANT = new ShipmentDuration();
    public static final ShipmentDuration SAME_DAY = new ShipmentDuration();
    public static final ShipmentDuration NEXT_DAY = new ShipmentDuration();
    public static final ShipmentDuration REGULER = new ShipmentDuration();
    public static final ShipmentDuration KARGO = new ShipmentDuration();
    private int bit;
    
    private ShipmentDuration (int bit)
    {
        this.bit = bit;
    }
    
    public ShipmentDuration (ShipmentDuration... args)
    {
        for(ShipmentDuration s:args)
        {
            if (s == ShipmentDuration.INSTANT)
                bit += 1 << 0;
            else if (s == ShipmentDuration.SAME_DAY)
                bit += 1 << 1;
            else if (s == ShipmentDuration.SAME_DAY)
                bit += 1 << 2;
            else if (s == ShipmentDuration.SAME_DAY)
                bit += 1 << 3;
            else 
                bit += 1 << 4;
        }
    }
    
    public boolean isDuration (ShipmentDuration reference)
    {
        return true;
    }
}
