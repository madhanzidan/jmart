package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

/**
 * Interface for predicate
 * @param <T>
 */
public interface Predicate<T> {
	public abstract boolean predicate(T arg);
}
