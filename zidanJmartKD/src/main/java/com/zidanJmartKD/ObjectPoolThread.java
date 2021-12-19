package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread{
	
	private boolean exitSignal;
	private Vector<T> objectPool = new Vector<>();
	private Function<T, Boolean> routine;

	/**
	 * Inisialisasi object pool thread
	 * @param name
	 * @param routine
	 */
	public ObjectPoolThread (String name, Function<T, Boolean> routine)
	{
		super(name);
		this.routine = routine;
	}

	/**
	 * Constructor untuk menjalankan routine object pool thread
	 * @param routine
	 */
	public ObjectPoolThread (Function<T, Boolean> routine)
	{
		super();
		this.routine = routine;
	}

	/**
	 * Menambahkan object pada objectPool
	 * @param object
	 */
	public synchronized void add (T object)
	{
		objectPool.add(object);
	}

	/**
	 * Memberi state pada exitSignal sebegai true
	 */
	public synchronized void exit ()
	{
		exitSignal = true;
	}

	/**
	 * Menjalankan object pool thread
	 */
	public void run() 
	{
		synchronized (this)
		{
			while (!exitSignal)
			{
				try {
					while (objectPool.size() != 0) 
					{
						for (T object : objectPool) {
							if (routine.apply(object)) {
								objectPool.remove(object);
							}
						}
					}
					this.wait();
				}
				
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return ukutan dari objectPool
	 */
	public int size ()
	{
		return objectPool.size();
	}

}
