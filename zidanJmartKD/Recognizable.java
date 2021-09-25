package zidanJmartKD;

public abstract class Recognizable
{
    public final int id;
    
    protected Recognizable (int id)
    {
        this.id = id;
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
}
