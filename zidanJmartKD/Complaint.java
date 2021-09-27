package zidanJmartKD;
public class Complaint extends Recognizable implements FileParser
{
    public String desc;
    public String date;
    
    
    public Complaint (int id, String desc)
    {
        super(id);
        this.desc = desc;
        desc = "1";
    }
    
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
    
}
