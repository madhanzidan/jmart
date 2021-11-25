package com.zidanJmartKD;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart
{
	public static long DELIVERED_LIMIT_MS = 100;
	public static long ON_DELIVERY_LIMIT_MS = 100;
	public static long ON_PROGRESS_LIMIT_MS = 100;
	public static long WAITING_CONF_LIMIT_MS = 100;
	
    public static void main(String[] args) 
    {
		SpringApplication.run(Jmart.class, args);
    }
}