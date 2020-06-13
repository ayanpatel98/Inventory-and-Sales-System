package com.wipro.sales.service;
import java.util.*;
import java.sql.*;
import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.util.DBUtil;

public class Administrator 
{
	StockDao sd= new StockDao();
	SalesDao so= new SalesDao();
	public String insertStock(Product stockobj) throws ClassNotFoundException, SQLException
	{
		if(stockobj!=null&& stockobj.getProductID().length()>=2)
		{
			String productID=sd.generateProductID(stockobj.getProductName());
			stockobj.setProductID(productID);
			if(sd.insertStock(stockobj) == 1) return productID;
			
			else return"Data not valid";
		}
		else
		{
			return "Data not valid";
		}
		
	}
	public String deleteStock(String ProductID) throws ClassNotFoundException, SQLException
	{
		if (sd.deleteStock(ProductID) == 1)
			return "deleted";
		else 
			return "record cannot be deleted";
	}
	public String insertSales(Sales salesobj) throws ClassNotFoundException, SQLException
	{
		if (salesobj == null) 
			return "Object not valid for insertion";
		
		if (sd.getStock(salesobj.getProductID()) == null)
			return "Unknown Product for sales";
		
		if (sd.getStock(salesobj.getProductID()).getQuantityOnHand() < salesobj.getQuantitySold())
			return "Not enough stock on hand for sales";
		
		
		
		String salesID = so.generateSalesID(salesobj.getSalesDate());
		salesobj.setSalesID(salesID);
		
		if (so.insertSales(salesobj) == 1) {
			if (sd.updateStock(salesobj.getProductID(), salesobj.getQuantitySold()) == 1)
				return "sales record inserted successfully";
			else 
				return "Error";
		} else {
			return "Error";
		}
	
		
	}
	
	public ArrayList<SalesReport> getSalesReport() throws ClassNotFoundException, SQLException
	{
		return so.getSalesReport();
	}
}
