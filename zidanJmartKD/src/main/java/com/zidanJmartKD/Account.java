package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import com.zidanJmartKD.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
	public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
	public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$)(?=.*[A-Z]).{8,}$";
	public double balance;
	public String email;
    public String name;
    public String password;
    public Store store;
  
    
    public Account (String name, String email, String password, double balance)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        store = null;
    }

    /**
     * Validate the email and password
     * @return validation of email and password
     */
    public boolean validate()
    {
        Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
        Matcher emailMatcher = emailPattern.matcher(email);
        
        Pattern passwordPattern = Pattern.compile(REGEX_PASSWORD);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        
        return emailMatcher.find() && passwordMatcher.find();
    }

    /**
     * @return name, email, and password of every account
     */
    public String toString() {
    	return "name: "+ name + "\nemail: " + email +"\npassword: " + password;
    }
}
