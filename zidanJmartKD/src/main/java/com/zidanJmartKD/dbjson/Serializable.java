package com.zidanJmartKD.dbjson;

import java.util.HashMap;

public class Serializable implements Comparable<Serializable>
{
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    public final int id;

    protected Serializable()
    {
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }

    public static <T extends Serializable> Integer setClosingId(Class<T> clazz, int id)
    {
        return mapCounter.put(clazz, id);
    }
    public static <T extends Serializable> Integer getClosingId(Class<T> clazz)
    {
        return mapCounter.get(clazz);
    }
    public boolean equals(Object other)
    {
        return other instanceof Serializable && ((Serializable) other).id == id;
    }
    public boolean equals(Serializable other)
    {
        return other.id == id;
    }

    @Override
    public int compareTo(Serializable other)
    {
        return Integer.compare(this.id, other.id);
    }
}

/*
import java.util.HashMap;

public class Serializable implements Comparable<Serializable>
{
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    public final int id;

    protected Serializable()
    {
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }

    public static <T extends Serializable> Integer setClosingId(Class<T> clazz, int id)
    {
        return mapCounter.put(clazz, id);
    }
    public static <T extends Serializable> Integer getClosingId(Class<T> clazz)
    {
        return mapCounter.get(clazz);
    }
    public boolean equals(Object other)
    {
        return other instanceof Serializable && ((Serializable) other).id == id;
    }
    public boolean equals(Serializable other)
    {
        return other.id == id;
    }

    @Override
    public int compareTo(Serializable other)
    {
        return Integer.compare(this.id, other.id);
    }
}





/*
import java.util.HashMap;
//import java.util.Map;


public class Serializable implements Comparable<Serializable>
{
    public final int id;
    
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    
    
    
    protected Serializable ()
    {
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }
    
    @Override
    public int compareTo (Serializable other)
    {
    	if(this.id == other.id)
    		return 0;
    	else if (this.id > other.id)
    		return 1;
    	else
    		return -1;
    }
    
    
    public boolean equals (Object other)
    {
        
        if (other instanceof Serializable)
        {
            Serializable i = (Serializable) other;
            return i.id == id ? true:false;
        }
        return false;
    }
    
    public boolean equals (Serializable other)
    {
        return id == other.id;
    }
    
    public static<T extends Serializable> Integer getClosingId(Class<T>clazz)
    {
    	return mapCounter.get(clazz);
    }
    
    public static<T extends Serializable> Integer setClosingId(Class<T>clazz, int id)
    {
    	return mapCounter.put(clazz, id);
    }
    
}

*/