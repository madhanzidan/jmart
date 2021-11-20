package com.zidanJmartKD.controller;
import com.zidanJmartKD.Account;
import com.zidanJmartKD.Algorithm;
import com.zidanJmartKD.Store;
import com.zidanJmartKD.dbjson.JsonAutowired;
import com.zidanJmartKD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$(?=\\S+$)";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD  = Pattern.compile(REGEX_PASSWORD);

    @JsonAutowired(filepath = "C:/Kuliah Semester 5/Java/jmart", value = Account.class)
    public static JsonTable<Account> accountTable;

    public JsonTable<Account> getJsonTable ()
    {
        return accountTable;
    }


    @PostMapping("/login")
    Account login(String email, String password)
    {
        /*
        for(Account account : accountTable)
        {
            if (account.email.equals(email) && account.password.equals(password))
                return account;
        }*/
        return null;
    }

    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        {
            if(!name.isBlank() && REGEX_PATTERN_EMAIL.matcher(email).find() && REGEX_PATTERN_PASSWORD.matcher(password).find() && !Algorithm.exists(accountTable.toArray(), email));
                return new Account(name, email, password, 0);
        }
    }

    @PostMapping("/{id}/registerStore")
    Store registerStore
            (
                    @RequestParam int id,
                    @RequestParam String name,
                    @RequestParam String address,
                    @RequestParam String phoneNumber
            )
    {
        if(Algorithm.exists(getJsonTable().toArray(), id) || !Algorithm.exists(getJsonTable().toArray(), name)) {
            return new Store(name, address, phoneNumber, 0);
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    boolean topUp (int id, double balance)
    {
        /*
        for(Account account : accountTable) {
            if(account.id == id) {
                account.balance += balance;
                return true;
            }
        }*/
        return false;
    }
/*
    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }*/
}
