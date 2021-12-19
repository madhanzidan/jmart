package com.zidanJmartKD;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import com.zidanJmartKD.dbjson.Serializable;

public class Product extends Serializable
{
    public int accountId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public double price;
    public double discount;
    public ProductCategory category;
    public byte shipmentPlans;

    /**
     * Menginisialisasi seluruh parameter product
     * @param accountId
     * @param name
     * @param weight
     * @param conditionUsed
     * @param price
     * @param discount
     * @param category
     * @param shipmentPlans
     */
    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans)
    {
    	this.accountId = accountId;
    	this.name = name;
    	this.weight = weight;
    	this.conditionUsed = conditionUsed;
    	this.price = price;
    	this.discount = discount;
    	this.category = category;
    	this.shipmentPlans = shipmentPlans;
    }

    /**
     * Shows product information to list view on product fragment
     * @return information of product
     */
    public String toString(){
        return 
        "accountId: " + accountId +
        "\nname: "+ name +
        "\nweight: " + weight +
        "\nconditionUsed: " + conditionUsed +
        "\nprice: " + price +
        "\ndiscount " + discount +
        "\ncategory: " + category +
        "\nshipmentPlans: " + shipmentPlans;
    }
}
