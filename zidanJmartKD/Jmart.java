package zidanJmartKD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.*;

public class Jmart
{
	class Country 
	{
		public String name;
		public int population;
		public List<String> listOfStates;
	}
	
    public static void main(String[] args) 
    {
    	try {
    		List<Product> list = read("jmart/randomProductList.json");
        	List<Product> filtered = filterByPrice(list, 0.0, 2000.0);
        	filtered.forEach(product -> System.out.println(product.price));
    	}
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}
    
    } 
    
    public static List<Product> filterByCategory (List<Product> list, ProductCategory category)
    {
    	List<Product> filteredList = new ArrayList<>();
    	
    	for(Product lists : list) 
    	{
    		if (lists.category == category) 
    		{
    			filteredList.add(lists);
    		}
    	}
    	return filteredList;
    	/*
    	String filepath = "jmart/randomProductList.json";
    	Gson gson = new Gson();
    	try
    	{
    		BufferedReader br = new BufferedReader(new FileReader(filepath));
    		Country input = gson.fromJson(br, Country.class);
    		System.out.println("name: " + input.name);
    		System.out.println("population: " + input.population);
    		System.out.println("states:");
    		input.listOfStates.forEach(state -> System.out.println(state));
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}*/
    }
    public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice)
    {
    	return null;
    }
    public static List<Product> read (String filepath)
    {
    	//final JsonReader reader = new JsonReader (new FileReader(filepath));
    	return null;
    	
    }
}