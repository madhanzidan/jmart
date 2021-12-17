package com.zidanJmartKD.controller;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import com.zidanJmartKD.Account;
import com.zidanJmartKD.Algorithm;
import com.zidanJmartKD.Store;
import com.zidanJmartKD.dbjson.JsonAutowired;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$)(?=.*[A-Z]).{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD  = Pattern.compile(REGEX_PASSWORD);

    @JsonAutowired(filepath = "C:\\Kuliah Semester 5\\Java\\jmart\\Account.json", value = Account.class)
    public static JsonTable<Account> accountTable;

    public JsonTable<Account> getJsonTable ()
    {
        return accountTable;
    }

    @PostMapping("/login")
    Account login(@RequestParam String email, @RequestParam String password)
    {
        String hashedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16). substring(1));
            hashedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for(Account account : accountTable)
        {
            if (account.email.equals(email) && account.password.equals(hashedPassword))
                return account;
        }
        return null;
    }

    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password)
    {
        String hashedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16). substring(1));
            hashedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if(!name.isBlank() && REGEX_PATTERN_EMAIL.matcher(email).find() &&
                REGEX_PATTERN_PASSWORD.matcher(password).find() &&
                !Algorithm.<Account>exists(getJsonTable(), (account -> account.email.equals(email))))
				{
					getJsonTable().add(new Account(name, email, hashedPassword, 0));
					return new Account(name, email, hashedPassword, 0);
				}
        return null;
    }

    @PostMapping("/{id}/registerStore")
    Store registerStore(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber)
    {
        //if(Algorithm.exists(getJsonTable().toArray(), id) || !Algorithm.exists(getJsonTable().toArray(), name)) {
            Account acc = Algorithm.<Account>find(getJsonTable(), (account -> account.id == id && account.store == null));
            acc.store = new Store(name, address, phoneNumber, 0);
            return acc.store;
        //}
       // return null;
    }

    @PostMapping("/{id}/topUp")
    boolean topUp (@PathVariable int id, @RequestParam double balance)
    {
        Account acc = Algorithm.<Account>find(getJsonTable(), (account -> account.id == id));
        acc.balance += balance;
        return Algorithm.<Account>exists(getJsonTable(), (account -> account.id == id));
    }

}
