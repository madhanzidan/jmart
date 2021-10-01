package zidanJmartKD;
public class Complaint extends Recognizable implements FileParser
{
    public String desc;
    public String date;
    
    
    public Complaint (int id, String desc)
    {
        super(id);
        this.desc = desc;
        date =  "1";
    }
    
    @Override //FileParser
    public boolean read (String content){
        return false;
    }
}
