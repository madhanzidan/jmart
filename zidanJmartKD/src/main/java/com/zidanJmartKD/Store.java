package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import com.zidanJmartKD.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store extends Serializable
{
    /**
     * Declare regex for name and phone number
     */
	public static final String REGEX_PHONE = "^[0-9]{9,12}\b";
    public static final String REGEX_NAME = "^[A-Z][a-z\\sa-z]{4,19}\b";

    /**
     * Declare other information for every account
     */
    public String address;
    public double balance;
    public String name;
    public String phoneNumber;

    /**
     * Inisialisasi informasi dari store
     * @param name
     * @param address
     * @param phoneNumber
     * @param balance
     */
    public Store (String name, String address, String phoneNumber, double balance)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    /**
     * Menentukan informasi string yang ditampilkan store
     * @return informasi store
     */
    public String toString(){
        return 
        "name: "+ name +
        "\naddress: " + address +
        "\nphoneNumber: " + phoneNumber ;
    }

    /**
     * @return kesesuaian input dengan regex phoneNumber dan name
     */
    public boolean validate()
    {
        Pattern teleponPattern = Pattern.compile(REGEX_PHONE);
        Matcher teleponMatcher = teleponPattern.matcher(phoneNumber);
        
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Matcher nameMatcher = namePattern.matcher(name);
        
        return teleponMatcher.find() && nameMatcher.find();
    }
}

