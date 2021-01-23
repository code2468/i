package com.wipro.sales.bean;

import java.util.Date;

public class SalesReport {
	String salesID, productID, productName;
	Date salesDate;
	int quantitySold;
	double productUnitPerPrice, salesPricePerUnit, profitAmount;
	
	
	public String getSalesID() {
		return salesID;
	}
	public void setSalesID(String salesID) {
		this.salesID = salesID;
	}
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public double getProductUnitPerPrice() {
		return productUnitPerPrice;
	}
	public void setProductUnitPerPrice(double productUnitPerPrice) {
		this.productUnitPerPrice = productUnitPerPrice;
	}
	public double getSalesPricePerUnit() {
		return salesPricePerUnit;
	}
	public void setSalesPricePerUnit(double salesPricePerUnit) {
		this.salesPricePerUnit = salesPricePerUnit;
	}
	public double getProfitAmount() {
		return profitAmount;
	}
	public void setProfitAmount(double profitAmount) {
		this.profitAmount = profitAmount;
	}
	public String toString()
	{
		return " | " + salesID + " | " + salesDate + " | " + productID + " | " + productName + " | " + 
				quantitySold + " | " + productUnitPerPrice + " | " + salesPricePerUnit 
				+ " | " + profitAmount +" |";
	}

}
