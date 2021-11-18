package com.zidanJmartKD;
import java.util.*;
import java.lang.Iterable;

public class Algorithm {
	private Algorithm() {
	}
	
	//List
	public static <T> List<T> collect(T[] array, T value) {
		Predicate<T> pred = val -> val.equals(value);
		List<T> list = new ArrayList<>();
		for (T index : array) {
			if (pred.predicate(index)) {
				list.add(index);
			}
		}
		return list;
	}
	
	public static <T> List<T> collect(Iterable<T> iterable, T value){
		Predicate<T> pred = val -> val.equals(value);
		List<T> list = new ArrayList<>();
		Iterator<T> iterator = iterable.iterator();
		
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				list.add(index);
			}
		}
		return list;
    }
	
	public static <T> List<T> collect(Iterator<T> iterator, T value){
		Predicate<T> pred = val -> val.equals(value);
		List<T> list = new ArrayList<>();
		
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.equals(index)) {
				list.add(index);
			}
		}
		return list;
    }
	
	public static <T> List<T> collect(T[] array, Predicate<T> pred) {
		List<T> list = new ArrayList<>();
		for (T index : array) {
			if (pred.predicate(index)) {
				list.add(index);
			}
		}
		return list;
	}
	
	public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
		List<T> list = new ArrayList<>();
		Iterator<T> iterator = iterable.iterator();
		
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				list.add(index);
			}
		}
		return list;
	}
	
	public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
		List<T> list = new ArrayList<>();
		
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				list.add(index);
			}
		}
		return list;
	}
	
	//int
	public static <T> int count(T[] array, T value) {
		int count = 0;
		Predicate<T> pred = val -> (val == value);
		for (T t : array) {
			if (pred.predicate(t)) {
				count++;
			}
		}
		return count;
	}
	
	public static <T> int count(Iterable<T> iterable, T value){
        int count = 0;
        Predicate<T> pred = val -> (val == value);
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
        	T index = iterator.next();
        	if (pred.predicate(index)) {
        		count++;
        	}
        }
        return count;
    }
	
	public static <T> int count(Iterator<T> iterator, T value){
		int count = 0;
		Predicate<T> pred = val -> (val == value);
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				count++;
			}
		}
		return count;
    }
	
	public static <T> int count (T[] array, Predicate<T> pred) {
		int count = 0;
		for(T t : array) {
			if (pred.predicate(t)) {
				count++;
			}
		}
		return count;
	}
	
	public static <T> int count (Iterable<T> iterable, Predicate<T> pred) {
		int count = 0;
		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				count++;
			}
		}
		return count;
	}
	
	public static <T> int count (Iterator<T> iterator, Predicate<T> pred) {
		int count = 0;
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				count++;
			}
		}
		return count;
	}
	
	//boolean
	public static <T> boolean exists (T[] array, T value){
		Predicate<T> pred = val -> (val == value);
		for (T t : array) {
			if (pred.predicate(t)) {
				return true;
			}
		}
		return false;
	}
	
	public static <T> boolean exists (Iterable<T> iterable, T value) {
		Predicate<T> pred = val -> (val == value);
		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				return true;
			}
		}
		return false;
	}
	
	public static <T> boolean exists (Iterator<T> iterator, T value) {
		Predicate<T> pred = val -> (val == value);
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				return true;
			}
		}
		return false;
	}
	
	public static <T> boolean exists (T[] array, Predicate<T> pred) {
		for (T t : array) {
			if (pred.predicate(t)) {
				return true;
			}
		}
		return false;
	}
	
	public static <T> boolean exists (Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
        	T index = iterator.next();
        	if (pred.predicate(index)) {
        		return true;
        	}
        }
        return false;
	}
	
	public static <T> boolean exists (Iterator<T> iterator, Predicate<T> pred) {
		while (iterator.hasNext()) {
        	T index = iterator.next();
        	if (pred.predicate(index)) {
        		return true;
        	}
        }
        return false;
	}
	
	//T
	public static <T> T find (T[] array, T value) {
		Predicate<T> pred = val -> (val == value);
		for (T t : array) {
			if (pred.predicate(t)) {
				return t;
			}
		}
		return null;
	}
	
	public static <T> T find  (Iterable<T> iterable, T value) {
		Predicate<T> pred = val -> (val == value);
		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				return index;
			}
		}
		return null;
	}
	
	public static <T> T find (Iterator<T> iterator, T value) {
		Predicate<T> pred = val -> (val == value);
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				return index;
			}
		}
		return null;
	}
	
	public static <T> T find  (T[] array, Predicate<T> pred) {
		for (T t : array) {
			if (pred.predicate(t)) {
				return t;
			}
		}
		return null;
	}
	
	public static <T> T find  (Iterable<T> iterable, Predicate<T> pred) {
		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				return index;
			}
		}
		return null;
	}
	public static <T> T find (Iterator<T> iterator, Predicate<T> pred) {
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (pred.predicate(index)) {
				return index;
			}
		}
		return null;
	}
	
	//T MAX
	public static <T extends Comparable<? super T>> T max (T first, T second) {
		if (first.compareTo(second)>0) {
			return first;
		}
		return second;
	}
	
	public static <T extends Comparable<? super T>> T max (T[] array) {
		T maximum = null;
		for (T t : array) {
			if (maximum == null) 
				maximum = t;
			else if (t.compareTo(maximum) > 0) {
				maximum = t;
			}
		}
		return maximum;
	}
	
	public static <T extends Comparable<? super T>> T max (Iterable<T> iterable) {
		T maximum = null;
		for (T index : iterable) {
			if (maximum == null) 
				maximum = index;
			else if (index.compareTo(maximum) > 0) {
				maximum = index;
			}
		}
		return maximum;
	}
	
	public static <T extends Comparable<? super T>> T max (Iterator<T> iterator) {
		T maximum = null;
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (maximum == null) 
				maximum = index;
			else if (index.compareTo(maximum) > 0) {
				maximum = index;
			}
		}
		return maximum;
	}
	
	public static <T extends Comparable<? super T>> T max (T first, T second, Comparator<? super T> comparator){
		int comp = comparator.compare(first, second);
		if(comp > 0) 
			return first;
		else
			return second;
	}
	
	public static <T extends Comparable<? super T>> T max (T[] array, Comparator<? super T> comparator){
		T maximum = array[0];
		for (int i = 0; i < array.length; i++) {
			int comp = comparator.compare(array[i], maximum);
			if (comp > 0) {
				maximum = array[i];
			}
		}
		return maximum;
	}
	
	public static <T extends Comparable<? super T>> T max (Iterable<T> iterable, Comparator<? super T> comparator){
		Iterator<T> iterator = iterable.iterator();
		T maximum = iterator.next();
		while (iterator.hasNext()) {
			T index = iterator.next();
			int comp = comparator.compare(index, maximum);
			if (comp > 0) {
				maximum = index;
			}
		}
		return maximum;
	}
	
	public static <T extends Comparable<? super T>> T max (Iterator<T> iterator, Comparator<? super T> comparator){
		T maximum = iterator.next();
		while (iterator.hasNext()) {
			T index = iterator.next();
			int comp = comparator.compare(index, maximum);
			if (comp > 0) {
				maximum = index;
			}
		}
		return maximum;
	}
	
	
	//T MIN
	public <T extends Comparable<? super T>> T min (T first, T second) {
		if (first.compareTo(second) < 0) {
			return first;
		}
		return second;
	}
	
	public static <T extends Comparable<? super T>> T min (T[] array) {
		T minimum = null;
		for (T index : array) {
			if (minimum == null) 
				minimum = index;
			else if (index.compareTo(minimum) > 0) {
				minimum = index;
			}
		}
		return minimum;
	}
	
	public static <T extends Comparable<? super T>> T min (Iterable<T> iterable) {
		T minimum = null;
		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (minimum == null)
				minimum = index;
			else if (index.compareTo(minimum) < 0)
				minimum = index;
		}
		return minimum;
	}
	
	public static <T extends Comparable<? super T>> T min (Iterator<T> iterator) {
		T minimum = null;
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (minimum == null)
				minimum = index;
			else if (index.compareTo(minimum) < 0)
				minimum = index;
		}
		return minimum;
	}
	
	public static <T extends Comparable<? super T>> T min (T first, T second, Comparator<? super T> comparator){
		int comp = comparator.compare(first, second);
		if (comp < 0) 
			return first;
		else
			return second;
	}
	
	public static <T extends Comparable<? super T>> T min (T[] array, Comparator<? super T> comparator){
		T minimum = null;
		for (T index : array) {
			if (minimum == null) {
				minimum = index;
			}
			else {
				int comp = comparator.compare(index, minimum);
				if (comp < 0)
					minimum = index;
			}
		}
		return minimum;
	}
	
	public static <T extends Comparable<? super T>> T min (Iterable<T> iterable, Comparator<? super T> comparator){
		T minimum = null;
		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (minimum == null) {
				minimum = index;
			}
			else {
				int comp = comparator.compare(index, minimum);
				if (comp < 0)
					minimum = index;
			}
		}
		return minimum;
	}
	
	public static <T extends Comparable<? super T>> T min (Iterator<T> iterator, Comparator<? super T> comparator){
		T minimum = null;
		while (iterator.hasNext()) {
			T index = iterator.next();
			if (minimum == null) {
				minimum = index;
			}
			else {
				int comp = comparator.compare(index, minimum);
				if (comp < 0)
					minimum = index;
			}
		}
		return minimum;
	}
	//List<T>
	public static <T> List<T> paginate (T[] array, int page, int pageSize, Predicate<T> pred)
	{
		List<T> paginationList = new ArrayList<>();
		if((pageSize < 0 || pageSize >= array.length) || (page < 0 || page >= array.length/pageSize))
		{
			throw new IllegalArgumentException();
		}
    	
    	for (T Lists : array)
    	{
    		if (pred.predicate(Lists))
    			paginationList.add(Lists);
    	}
    	
    	
    	int fromIndex = page * pageSize;
    	
     	if (paginationList == null || paginationList.size() <= fromIndex)
    		return Collections.emptyList();
    	
    	return paginationList.subList(fromIndex, Math.min(fromIndex + pageSize, paginationList.size()));	
	}
	
	public static <T> List<T> paginate (Iterable<T> iterable, int page, int pageSize, Predicate<T> pred)
	{
		List<T> paginationList = new ArrayList<>();
		Iterator<T> iterator = iterable.iterator();
		
		int iteratorSize = ((Collection<?>) iterable).size();
		
		if((pageSize < 0 || pageSize >= iteratorSize) || (page < 0 || page >= iteratorSize/pageSize))
		{
			throw new IllegalArgumentException();
		}
		
		while (iterator.hasNext())
		{
			T index = iterator.next();
			if (pred.predicate(index));
				paginationList.add(index);
		}
			 
		int fromIndex = page * pageSize;
		
    	if (paginationList == null || paginationList.size() <= fromIndex)
    		return Collections.emptyList();
    	
    	return paginationList.subList(fromIndex, Math.min(fromIndex + pageSize, paginationList.size()));
	}
	
	
	public static <T> List<T> paginate (Iterator<T> iterator, int page, int pageSize, Predicate<T> pred)
	{
		List<T> paginationList = new ArrayList<>();
		int iteratorSize = ((Collection<?>) iterator).size();
		
		if((pageSize < 0 || pageSize >= iteratorSize) || (page < 0 || page >= iteratorSize/pageSize))
		{
			throw new IllegalArgumentException();
		}
		while (iterator.hasNext())
		{
			T index = iterator.next();
			if (pred.predicate(index));
				paginationList.add(index);
		}
		
		int fromIndex = page * pageSize;
		
    	if (paginationList == null || paginationList.size() <= fromIndex)
    		return Collections.emptyList();
    	
    	return paginationList.subList(fromIndex, Math.min(fromIndex + pageSize, paginationList.size()));
		
	}
	
}


