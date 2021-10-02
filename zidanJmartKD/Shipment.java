package zidanJmartKD;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Shipment implements FileParser
{
    
    public static class Duration
    {
        public static final Duration INSTANT = new Duration((byte)(1 << 0));
        public static final Duration SAME_DAY = new Duration((byte)(1 << 1));
        public static final Duration NEXT_DAY = new Duration((byte)(1 << 2));
        public static final Duration REGULER = new Duration((byte)(1 << 3));
        public static final Duration KARGO = new Duration((byte)(1 << 4));
        private byte bit;
    
        public static final SimpleDateFormat ESTIMATION_FORMAT = 
            new SimpleDateFormat ("EEE MMMM dd yyyy");
        
        private Duration (byte bit)
        {
            this.bit = bit;
        }
        
        public Duration (Duration... args)
        {
            for(Duration i:args)
                this.bit |= i.bit;
        }
        
        public boolean isDuration (Duration reference)
        {
            if((this.bit & reference.bit) != 0)
                return true;
            else
                return false;
        }
        
          public String getEstimatedArrival (Date reference)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(reference);
            if (this.bit == INSTANT.bit || this.bit == SAME_DAY.bit)
                return ESTIMATION_FORMAT.format(reference);
            else if (this.bit == Duration.NEXT_DAY.bit){
                cal.add (Calendar.DATE,1);
                return ESTIMATION_FORMAT.format(cal.getTime());
            }    
            else if (this.bit == Duration.REGULER.bit){
                cal.add (Calendar.DATE,2);
                return ESTIMATION_FORMAT.format(cal.getTime());
            }
            else{
                cal.add (Calendar.DATE,5);
                return ESTIMATION_FORMAT.format(cal.getTime());           
            }
        }
    }
    public class MultiDuration
    {
        public byte bit;
        
        public MultiDuration (Duration... args)
        {
           for(Duration i:args)
                this.bit |= i.bit; 
        }
        public boolean isDuration (Duration reference)
        {
            if((this.bit & reference.bit) != 0)
                return true;
            else
                return false;
        }
    }
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    public Shipment(String address, int shipmentCost, Duration duration, String receipt)
    {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }
    
     public boolean read (String content){
        return false;
    }
    
}

