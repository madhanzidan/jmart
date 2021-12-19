package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

public class PhoneTopUp extends Invoice{
	String phoneNumber;
	Status status;

	/**
	 * Menginisialisasi id dan phone number dalam melakukan top up
	 * @param buyerId
	 * @param productId
	 * @param phoneNumber
	 */
	public PhoneTopUp (int buyerId, int productId, String phoneNumber)
	{
		super(buyerId, productId);
		this.phoneNumber = phoneNumber;
	}

	/**
	 * get total pay of product
	 * @param product
	 * @return
	 */
	@Override
	public double getTotalPay (Product product)
	{
		return product.price - (product.price * product.discount);
	}

}
