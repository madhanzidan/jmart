package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import com.zidanJmartKD.dbjson.Serializable;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Complaint extends Serializable
{
	public final Date date;
	public String desc;

    /**
     * Inisialisasi desc dan date untuk compaint
     * @param desc
     */
    public Complaint (String desc)
    {
        this.desc = desc;
        date = new Date();
    }

    /**
     * @return complaint dengan date sesuai simple date format
     */
    public String toString()
    {
        SimpleDateFormat SDComplaint
            = new SimpleDateFormat ("dd/MM/yyy");
        
        return "Complaint{date=" + SDComplaint.format(date) + ", desc='" + desc + "'}" ;
    }
}
