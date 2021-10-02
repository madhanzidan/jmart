package zidanJmartKD;
import java.util.Date;

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
}
