package zidanJmartKD;

public class Recognizable implements Comparable<Recognizable>
{
    public final int id;
    
    protected Recognizable ()
    {
        this.id = 777;
    }
    
    @Override
    public int compareTo (Recognizable recognizable)
    {
    	if(this.id == recognizable.id)
    		return 0;
    	else if (this.id > recognizable.id)
    		return 1;
    	else
    		return -1;
    }
    
    
    public boolean equals (Object newObject)
    {
        
        if (newObject instanceof Recognizable)
        {
            Recognizable i = (Recognizable) newObject;
            return i.id == id ? true:false;
        }
        return false;
    }
    
    public boolean equals (Recognizable recognizable)
    {
        return id == recognizable.id;
    }
    
    public static<T extends Recognizable> int getClosingId(Class<T>clazz)
    {
    	return 0;
    }
    
    public static<T extends Recognizable> int setClosingId(Class<T>clazz, int id)
    {
    	return 0;
    }
    
}
