package com.wipro.sales.dao;

import java.sql.*;
import java.util.*;

import com.wipro.sales.util.*;
import com.wipro.sales.bean.*;

public class SalesDao {
  PreparedStatement ps;
  Connection con;
  
  public int insertSales(Sales sales)
  {
	  con = DBUtil.getDBConnection();
	  try
	  {
		  ps = con.prepareStatement("INSERT into TBL_SALES values (?, ?, ?, ?, ?)");
		  ps.setString(1, sales.getSalesID());
		  java.sql.Date sqlDate = new java.sql.Date(sales.getSalesDate().getTime());
		  ps.setDate(2,  sqlDate);
		  ps.setString(3,  sales.getProductID());
		  ps.setInt(4,  sales.getQuantitySold());
		  ps.setDouble(5, sales.getSalesPricePerUnit());
		  ps.executeUpdate();
		  return 1;
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  return 0;
  }
  
  public String generateSalesID(java.util.Date salesDate)
  {
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(salesDate);
	  int temp = cal.get(Calendar.YEAR) % 100;
	  con = DBUtil.getDBConnection();
	  try 
	  {
		  ps = con.prepareStatement("SELECT ANKITA_1810991055.SEQ_SALES_ID.nextval from dual");
		  ResultSet rs = ps.executeQuery();
		  if (rs.next())
			  return temp + "" + rs.getInt(1);
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  return "";
  }
  
  public ArrayList<SalesReport> getSalesReport()
  {
	  ArrayList<SalesReport> al = new ArrayList<>();
	  con = DBUtil.getDBConnection();
	  try
	  {
		  ps = con.prepareStatement("SELECT * from ANKITA_1810991055.V_SALES_REPORT");
		  ResultSet rs = ps.executeQuery();
		  while(rs.next()) 
		  {
		      SalesReport s1 = new SalesReport();
		      s1.setSalesID(rs.getString(1));
		      s1.setSalesDate(rs.getDate(2));
		      s1.setProductID(rs.getString(3));
		      s1.setProductName(rs.getString(4));
		      s1.setQuantitySold(rs.getInt(5));
		      s1.setProductUnitPerPrice(rs.getDouble(6));
		      s1.setSalesPricePerUnit(rs.getDouble(7));
		      s1.setProfitAmount(rs.getDouble(8));
		      al.add(s1);	
		  }
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  return al;
  }
}