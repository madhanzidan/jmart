package zidanJmartKD;

import java.io.*;
import java.util.Vector;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Array;

public class JsonTable<T> extends Vector{
	public final String filepath;
	private static final Gson gson = new Gson();
	
	public JsonTable (Class<T> clazz, String filepath) throws IOException
	{
		this.filepath = filepath;
		@SuppressWarnings("unchecked")
		Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
		T[] loaded = readJson (arrayType, filepath);
		
		for(T index:loaded)
			this.add(index);
		
		String filewrite = "C:\\Kuliah Semester 5\\Java\\jmart\\filewrite.json";
		writeJson(loaded, filewrite);
	}
	
	public static <T> T readJson (Class<T> clazz, String filepath) throws FileNotFoundException
	{
		final JsonReader reader = new JsonReader (new FileReader(filepath));
		return gson.fromJson(reader, clazz);
	}
	
	public void writeJson () throws IOException
	{
		final FileWriter writer = new FileWriter (this.filepath);
		gson.toJson(gson, writer);
	}
	
	public static void writeJson (Object object, String filepath) throws IOException
	{
		final FileWriter writer = new FileWriter(filepath);
		gson.toJson(object, writer);
	}
}