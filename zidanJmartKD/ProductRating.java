package zidanJmartKD;

public class ProductRating
{
	private long count;
    private long total;

    public ProductRating()
    {
        total = 0;
        count = 0;
    }
    
    public double getAverage()
    {
        if (count == 0)
            return 0;
        else
            return total / count;
    }
    
    public long getCount()
    {
        return count;
    }
    
    public long getTotal()
    {
        return total;
    }
    
    public void insert (int rating)
    {
        total += rating;
        count++;
    }
    
}
