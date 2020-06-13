package com.wipro.sales.dao;
import java.util.*;
import java.sql.*;

import com.wipro.sales.bean.*;
import com.wipro.sales.util.DBUtil;
public class SalesDao 
{
	Connection conn=null;
	PreparedStatement ps=null;
	
	DBUtil db= new DBUtil();
	
	
	public int insertSales(Sales sales) throws ClassNotFoundException, SQLException
	{
		conn=db.getDBConnection();
		String sql="INSERT INTO TBL_SALES VALUES(?,?,?,?,?)";
		java.sql.Date sqlDate = new java.sql.Date(sales.getSalesDate().getTime());
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, sales.getSalesID());
		ps.setDate(2, sqlDate);
		ps.setString(3,sales.getProductID());
		ps.setInt(4,sales.getQuantitySold());
		ps.setDouble(5, sales.getSalesPricePerUnit());
		if(ps.executeUpdate()==1)
		return 1;
		else
			return 0;
		
	}
	
	public String generateSalesID(java.util.Date date) throws ClassNotFoundException, SQLException
	{
		conn=db.getDBConnection();
		String sql="SELECT SEQ_SALES_ID.NEXTVAL FROM DUAL";
		int SEQ_SALES_ID=0;
		String out="";
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		rs.next();
		SEQ_SALES_ID=rs.getInt(1);
		out+=SEQ_SALES_ID;
		return out;
		}
	
	public ArrayList<SalesReport> getSalesReport() throws ClassNotFoundException, SQLException
	{
		conn=db.getDBConnection();
		String sql="SELECT * FROM V_SALES_REPORT";
		ps=conn.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		ArrayList<SalesReport> list=new ArrayList<>();

		while(rs.next())
		{
			SalesReport sr=new SalesReport();
			sr.setSalesID(rs.getString(1));
			sr.setSalesDate(rs.getDate(2));
			sr.setProductID(rs.getString(3));
			sr.setProductName(rs.getString(4));
			sr.setQuantitySold(rs.getInt(5));
			sr.setProductUnitPrice(rs.getDouble(6));
			sr.setSalesPricePerUnit(rs.getDouble(7));
			sr.setProfitAmount(rs.getDouble(8));
			list.add(sr);
		}
		return list;
	}
}
