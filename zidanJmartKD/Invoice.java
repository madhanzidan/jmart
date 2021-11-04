package zidanJmartKD;
import java.util.Date;
import java.util.ArrayList;

public abstract class Invoice extends Recognizable
{
	public int buyerId;
	public int complaintId;
    public Date date;
    public ArrayList<Record> history;
    public int productId;
    public Rating rating;
    public Status status;
    
    
    protected Invoice (int buyerId, int productId)
    {
        this.buyerId = buyerId;
        this.productId = productId;
        //date = "10 November 1945";
        rating = Rating.NONE;
        status = Status.WAITING_CONFIRMATION;
    }
    
    public abstract double getTotalPay();
    
    //Enum
    public static enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }
    public static enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY,
        COMPLAINT, FINISHED, FAILED
    }
    
    public class Record
    {
    	public Date date;
    	public String message;
        public Status status;
    }
}
