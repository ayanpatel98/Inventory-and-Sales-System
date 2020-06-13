package com.wipro.sales.dao;

import java.sql.*;
import com.wipro.sales.bean.*;

import com.wipro.sales.util.DBUtil;

public class StockDao
{
	Connection conn=null;
	PreparedStatement ps=null;
	DBUtil db= new DBUtil();
	public int insertStock(Product sales) throws ClassNotFoundException, SQLException
	{
		conn=db.getDBConnection();
		String sql="INSERT INTO TBL_STOCK VALUES(?,?,?,?,?)";
		ps=conn.prepareStatement(sql);
		ps.setString(1,sales.getProductID());
		ps.setString(2,sales.getProductName());
		ps.setInt(3, sales.getQuantityOnHand());
		ps.setDouble(4, sales.getProductUnitPrice());
		ps.setInt(5,sales.getReorderLevel());
		if (ps.executeUpdate() == 1) return 1;
		else return 0;
	}
	public String generateProductID(String productName) throws ClassNotFoundException, SQLException
	{
		conn=db.getDBConnection();
		String sql="SELECT SEQ_PRODUCT_ID.NEXTVAL FROM DUAL";
		int SEQ_PRODUCT_ID=0;
		String out="";
		
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		rs.next();
		SEQ_PRODUCT_ID =rs.getInt(1);
		out=productName.substring(0,2);
		out+=SEQ_PRODUCT_ID;
		return out;
	}
	public int updateStock(String productID,int soldQty) throws ClassNotFoundException, SQLException
	{
		conn=db.getDBConnection();
		String sql="UPDATE TBL_STOCK SET Quantity_On_Hand=Quantity_On_Hand-? WHERE Product_ID=? ";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, soldQty);
		ps.setString(2, productID);
		if (ps.executeUpdate() == 1) return 1;
		else return 0;

	}
	
	public Product getStock(String productID) throws SQLException, ClassNotFoundException
	{
		conn=db.getDBConnection();
		String sql="SELECT * FROM TBL_STOCK WHERE Product_ID=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, productID);
		ResultSet rs=ps.executeQuery();
		rs.next();
		Product pro= new Product();
		pro.setProductID(rs.getString(1));
		pro.setProductName(rs.getString(2));
		pro.setQuantityOnHand(rs.getInt(3));
		pro.setProductUnitPrice(rs.getDouble(4));
		pro.setReorderLevel(rs.getInt(5));
		return pro;
	}
	
	public int deleteStock(String productID) throws ClassNotFoundException, SQLException
	{
		conn=db.getDBConnection();
		String sql="DELETE TBL_STOCK WHERE Product_ID=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, productID);
		if (ps.executeUpdate() == 1) return 1;
		else return 0;
	}
	
}
