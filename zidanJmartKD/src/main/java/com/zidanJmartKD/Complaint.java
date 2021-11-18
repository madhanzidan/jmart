package com.zidanJmartKD;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Complaint extends Serializable
{
	public final Date date;
	public String desc;
	
    public Complaint (String desc)
    {
        this.desc = desc;
        date = new Date();
    }
    
    public String toString()
    {
        SimpleDateFormat SDComplaint
            = new SimpleDateFormat ("dd/MM/yyy");
        
        return "Complaint{date=" + SDComplaint.format(date) + ", desc='" + desc + "'}" ;
    }
}
