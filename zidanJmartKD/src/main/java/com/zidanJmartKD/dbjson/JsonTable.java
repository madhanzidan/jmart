package com.zidanJmartKD.dbjson;

import java.io.*;
import java.util.Collections;
import java.util.Vector;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Array;

public class JsonTable<T> extends Vector<T>{
	public final String filepath;
	private static final Gson gson = new Gson();
	
	public JsonTable (Class<T> clazz, String filepath) throws IOException
	{
		this.filepath = filepath;
		try
		{
			@SuppressWarnings("unchecked")
			Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
			T[] loaded = readJson (arrayType, filepath);
			
			if(loaded != null)
			{
				Collections.addAll(this, loaded);
			}
		}
		catch (FileNotFoundException e)
		{
			File file = new File(filepath);
			File file1 = file.getParentFile();
			if (file1 != null)
				file1.mkdirs();
			file.createNewFile();
		}
	}
	
	public static <T> T readJson (Class<T> clazz, String filepath) throws FileNotFoundException
	{
		JsonReader reader = new JsonReader (new FileReader(filepath));
		T obj = gson.fromJson(reader, clazz);
		return obj;
	}
	
	public void writeJson () throws IOException
	{
		writeJson (this, this.filepath);
	}
	
	public static void writeJson (Object object, String filepath) throws IOException
	{
		FileWriter fileWriter = new FileWriter(filepath);
		String s = gson.toJson(object);
		fileWriter.write(s);
		fileWriter.close();
	}
}