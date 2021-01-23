package com.wipro.sales.dao;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.wipro.sales.bean.*;
import com.wipro.sales.util.DBUtil;

import java.sql.*;

public class StockDao {
	//PreparedStatement ps;
	Connection con;
	
	public int insertStock(Product sales)
	{
		con = DBUtil.getDBConnection();
		try
		  {
			PreparedStatement ps = con.prepareStatement("INSERT into TBL_STOCK values (?, ?, ?, ?, ?)");
			  ps.setString(1, sales.getProductID());
			  ps.setString(2,  sales.getProductName());
			  ps.setInt(3,  sales.getQuantityOnHand());
			  ps.setDouble(4,  sales.getProductUnitPrice());
			  ps.setInt(5, sales.getReorderLevel());
			  ps.executeUpdate();
			  return 1;
		  }
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  }
		return 0;
	}
	
	public String generateProductID(String productName)
	{
		con = DBUtil.getDBConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("SELECT ANKITA_1810991055.SEQ_PRODUCT_ID.nextval from dual");
			 ResultSet rs = ps.executeQuery();
			 if (rs.next())
			 {				 
				 return productName.substring(0, 2) + rs.getInt(1);	
			 }
				  		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public int updateStock(String productID, int soldQty) throws Exception
	{
		con = DBUtil.getDBConnection();
		try
		{
			PreparedStatement st = con.prepareStatement("select quantity_on_hand from TBL_STOCK where product_id=?");
			st.setString(1, productID);
			ResultSet rs = st.executeQuery();
			rs.next();
			int newQuantity = rs.getInt("quantity_on_hand") - soldQty;
			st=con.prepareStatement("update TBL_STOCK set quantity_on_hand=? where product_id=?");
			st.setInt(1, newQuantity);
			st.setString(2,productID);
			st.executeUpdate();
			return 1;
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	Product getStock(String productID) throws Exception
	{
		Product stock = new Product();
		con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM TBL_STOCK WHERE Product_ID='" + productID + "'");
	    stock.setProductID(rs.getString("Product_ID"));
	    stock.setProductName(rs.getNString("Product_Name"));
	    stock.setQuantityOnHand(rs.getInt("Quantity_On_Hand"));
	    stock.setProductUnitPrice(rs.getDouble("Product_Unit_Price"));
	    stock.setReorderLevel(rs.getInt("Reorder_Level"));
		return stock;
	}
	
	public int deleteStock(String productID)
	{
		con = DBUtil.getDBConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("DELETE from TBL_STOCK where PRODUCT_ID=?");
			ps.setString(1, productID);
			ps.executeQuery();
			return 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

}
