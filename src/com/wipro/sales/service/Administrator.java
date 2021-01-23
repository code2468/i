package com.wipro.sales.service;

import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Administrator 
{
	
	public static String insertStock(Product stockobj)
	{
		StockDao sd = new StockDao();
		if (stockobj.getProductName().length() < 2)
			return "Data not valid for insertion, Product's Name length less than 2.";
		else
		{			
			String generatedID = sd.generateProductID(stockobj.getProductName());
			stockobj.setProductID(generatedID);
			if (sd.insertStock(stockobj) == 1)
				return "Record with Product ID : " + generatedID +", generated successfully.";
			else
				return "Data not valid for insertion";
		}
	}
	
	public static String deleteStock(String ProductID)
	{
		StockDao sd = new StockDao();
		if (sd.deleteStock(ProductID) == 1)
			return "Record successfully deleted.";
		else
			return "Record cannot be deleted";
	}
	
	public static String insertSales(Sales salesobj)
	{
		Connection con = DBUtil.getDBConnection();
		SalesDao saleDao = new SalesDao();
		StockDao stockDao = new StockDao();
		if (salesobj != null)
		{
			try
			{
				PreparedStatement ps = con.prepareStatement("SELECT Quantity_On_Hand from TBL_STOCK where Product_ID=?");
				ps.setString(1, salesobj.getProductID());
				ResultSet rs = ps.executeQuery();
				int value = 0;
				if (rs.next())
					value = rs.getInt("Quantity_On_Hand");
				int temp = ps.executeUpdate();
				if (temp == 0)
					return "Product ID doeasn't exist.";
				else
				{
					if (value < salesobj.getQuantitySold())
						return "Not enough stock on hand for sale.";
					else
					{
						Date currDate = new Date();
						Date tempDate = salesobj.getSalesDate();
						if (tempDate.compareTo(currDate) > 0)
							return "Invalid Date.";
						else
						{
							salesobj.setSalesID(saleDao.generateSalesID(tempDate));
							if (saleDao.insertSales(salesobj) == 1)
							{
								if (stockDao.updateStock(salesobj.getProductID(), salesobj.getQuantitySold()) == 1)
									return "Sales successfully completed.";
								else
									return "Error!";
							}
							else
								return "Data can not be inserted.";
						}
					}
				}	
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return "";
		}
		else
			return "Sales Object not valid for insertion";
	}
	
	public static ArrayList<SalesReport> getSalesReport()
	{
		ArrayList<SalesReport> al = new ArrayList<>();
		SalesDao sd = new SalesDao();
		al = sd.getSalesReport();
		return al;
	}
	
}
