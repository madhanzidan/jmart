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
	/*
	class Country 
	{
		public String name;
		public int population;
		public List<String> listOfStates;
	}*/
	
    public static void main(String[] args) 
    {
		SpringApplication.run(Jmart.class, args);
    	/*
    	try {
    		JsonTable<Payment> table = new JsonTable<> (Payment.class, "C:/Kuliah Semester 5/Java/jmart/randomPaymentList.json");
    		
    		ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP", Jmart::paymentTimekeeper);
    		
    		paymentPool.start();
    		
    		table.forEach(payment -> paymentPool.add(payment));
    		
    		while (paymentPool.size() != 0);
    		paymentPool.exit();
    		
    		while (paymentPool.isAlive());
    		
    		System.out.println("Thread exited successfully");
    		
    		Gson gson = new Gson();
    		
    		table.forEach(payment -> {
    			String history = gson.toJson(payment.history);
    			System.out.print(history);
    		});
    		
    	}
    	
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}
    	*/
    	/*
    	try {
    		
    		List<Product> list = read("C:/Kuliah Semester 5/Java/jmart/randomProductList.json");
        	//List<Product> filtered = filterByPrice(list, 19000.0, 20000.0);
        	//filtered.forEach(product -> System.out.println(product.price));
        	//filtered.forEach(product -> System.out.println(product.price));
    		List<Product> filteredName = filterByName(list, "gtx", 1, 5);
        	filteredName.forEach(product -> System.out.println(product.name));
        	
    		List<Product> filteredId = filterByAccountId(list, 1, 0, 5);
    		filteredId.forEach(product -> System.out.println(product.name));
    		
    		String filepath = "C:/Kuliah Semester 5/Java/jmart/account.json";
    		
    		JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
    		tableAccount.add(new Account("name", "email", "password", 10000));
    		tableAccount.writeJson();
    		
    		tableAccount = new JsonTable<>(Account.class, filepath);
    		tableAccount.forEach(account -> System.out.println(account.toString()));
    
    	}
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}
    	*/
    } 
    
    public static boolean paymentTimekeeper (Payment payment)
    {
    	long elapsedTime = (new java.util.Date()).getTime() - payment.history.get(payment.history.size()-1).date.getTime();
    	Date currentDate = new Date();
    	
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION && elapsedTime > WAITING_CONF_LIMIT_MS)
    		payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Fail Process"));
    	else if (payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_PROGRESS && elapsedTime > ON_PROGRESS_LIMIT_MS)
    		payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Fail Process"));
    	else if (payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_DELIVERY && elapsedTime > ON_DELIVERY_LIMIT_MS)
    		payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "On Process"));
    	else if (payment.history.get(payment.history.size()-1).status == Invoice.Status.DELIVERED && elapsedTime > DELIVERED_LIMIT_MS)
    		payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Process Success"));
    	
    	for (Payment.Record index : payment.history)
    	{
    		if (index.status == Invoice.Status.FINISHED || index.status == Invoice.Status.FAILED)
    			payment.history.remove(index);
    	}
    	
    	if (payment.history.isEmpty())
    		return true;
    	else
    		return false;
    }
    /*
    public static List<Product> filterByCategory (List<Product> list, ProductCategory category)
    {
    	List<Product> filteredCategoryList = new ArrayList<>();
    	
    	for(Product lists : list) 
    	{
    		if (lists.category == category) 
    			filteredCategoryList.add(lists);
    	}
    	return filteredCategoryList;
    }*/
    /*
    public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice)
    {
    	List<Product> filteredPriceList = new ArrayList<>();
    	
    	if (minPrice == 0.0 && maxPrice == 0.0)
    		return null;
    	else if (minPrice == 0.0)
    	{
    		for (Product lists : list)
    		{
    			if (lists.price <= maxPrice)
    				filteredPriceList.add(lists);
    		}
   
    	}
    	else if (maxPrice == 0.0)
    	{
    		for (Product lists : list)
    		{
    			if (lists.price >= minPrice)
    				filteredPriceList.add(lists);
    		}
    	}
    	else
    	{
    		for (Product lists : list)
    		{
    			if (lists.price >= minPrice && lists.price <= maxPrice)
    				filteredPriceList.add(lists);
    		}
    	}	
    	return filteredPriceList;
    }*/
    /*
    public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize)
    {
    	List<Product> filteredAccountIdList = new ArrayList<>();
    	Predicate<Product> pred = product -> product.accountId == accountId;
    	filteredAccountIdList = paginate (list, page, pageSize, pred);
    
    	return filteredAccountIdList;
    }*/
    
    /*
    public static List<Product> filterByName (List<Product> list, String search, int page, int pageSize)
    {
    	List<Product> filteredNameList = new ArrayList<>();
    	Predicate<Product> pred = product -> product.name.toLowerCase().contains(search.toLowerCase());
    	filteredNameList = paginate (list, page, pageSize, pred);
    	
    	return filteredNameList;
    }*/
    
    /*
    private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred)
    {
    	List<Product> paginationList = new ArrayList<>();
    	
    	for (Product Lists : list)
    	{
    		if (pred.predicate(Lists))
    			paginationList.add(Lists);
    	}
    	
    	if (pageSize <= 0 || page < 0)
    		throw new IllegalArgumentException ("Invalid page size: " + pageSize);
    	
    	int fromIndex = page * pageSize;
    	if(paginationList == null || paginationList.size() <= fromIndex)
    		return Collections.emptyList();
    	
    	return paginationList.subList(fromIndex, Math.min(fromIndex + pageSize, paginationList.size()));	
    }*/
    
    /*
    public static List<Product> read (String filepath) throws FileNotFoundException
    {
    	final JsonReader reader = new JsonReader (new FileReader(filepath));
    	Product[] data = new Gson().fromJson(reader, Product[].class);
    	List<Product> list = new ArrayList<>();
    	Collections.addAll(list, data);
    	return list;
    }*/
}