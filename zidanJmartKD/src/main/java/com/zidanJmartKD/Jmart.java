package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.zidanJmartKD.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart
{
	public static long DELIVERED_LIMIT_MS = 100;
	public static long ON_DELIVERY_LIMIT_MS = 100;
	public static long ON_PROGRESS_LIMIT_MS = 100;
	public static long WAITING_CONF_LIMIT_MS = 100;

	/**
	 * Run json db engine and springboot
	 * @param args
	 */
    public static void main(String[] args) 
    {
		JsonDBEngine.Run(Jmart.class);
		SpringApplication.run(Jmart.class, args);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }
}