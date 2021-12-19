package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

public class ProductRating
{
	private long count;
    private long total;

    /**
     * Inisialisasi nilai awal total dan count dari rating
     */
    public ProductRating()
    {
        total = 0;
        count = 0;
    }

    /**
     * @return average dari nilai rating
     */
    public double getAverage()
    {
        if (count == 0)
            return 0;
        else
            return total / count;
    }

    /**
     * Mendapatkan jumlah rating
     * @return count dari rating
     */
    public long getCount()
    {
        return count;
    }

    /**
     * Mendapatkan total dari rating
     * @return total dari rating
     */
    public long getTotal()
    {
        return total;
    }

    /**
     * Insert rating
     * @param rating
     */
    public void insert (int rating)
    {
        total += rating;
        count++;
    }
    
}
