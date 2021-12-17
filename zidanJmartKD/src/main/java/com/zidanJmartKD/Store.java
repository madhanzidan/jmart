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
	public static final String REGEX_PHONE = "^[0-9]{9,12}\b";
    public static final String REGEX_NAME = "^[A-Z][a-z\\sa-z]{4,19}\b";
    public String address;
    public double balance;
    public String name;
    public String phoneNumber;
    
    public Store (String name, String address, String phoneNumber, double balance)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    
    public String toString(){
        return 
        "name: "+ name +
        "\naddress: " + address +
        "\nphoneNumber: " + phoneNumber ;
    }
    
    public boolean validate()
    {
        Pattern teleponPattern = Pattern.compile(REGEX_PHONE);
        Matcher teleponMatcher = teleponPattern.matcher(phoneNumber);
        
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Matcher nameMatcher = namePattern.matcher(name);
        
        return teleponMatcher.find() && nameMatcher.find();
    }
}

