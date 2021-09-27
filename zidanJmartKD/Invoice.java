package zidanJmartKD;

public abstract class Invoice extends Recognizable implements FileParser
{
    public String date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    
    protected Invoice (int id, int buyerId, int productId)
    {
        super(id);
        this.buyerId = buyerId;
        this.productId = productId;
        date = "10 November 1945";
        rating = Rating.NONE;
        status = Status.WAITING_CONFIRMATION;
    }
    
    public abstract double getTotalPay();
    
    
    //FileParser
     @Override
    public boolean read (String content){
        return false;
    }
    @Override
    public Object write()
    {
        return null;
    }
    public Object newInstance (String content)
    {
        return null;
    }
    
    //Enum
    public static enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }
    public static enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY,
        COMPLAINT, FINISHED, FAILED
    }
}
