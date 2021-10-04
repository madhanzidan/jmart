package zidanJmartKD;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Complaint extends Recognizable implements FileParser
{
    public String desc;
    public Date date;
    public Complaint (int id, String desc)
    {
        super(id);
        this.desc = desc;
        Date date = new Date();
    }
    
    @Override //FileParser
    public boolean read (String content){
        return false;
    }
    
    public String toString()
    {
        SimpleDateFormat SDComplaint
            = new SimpleDateFormat ("dd/MM/yyy");
        
        return "Complaint{date=" + SDComplaint.format(date) + ", desc='" + desc + "'}" ;
    }
}
