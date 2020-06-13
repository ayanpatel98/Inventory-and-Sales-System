package com.wipro.sales.util;
import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.service.*;
import java.sql.*;

public class DBUtil 
{
	
	public Connection getDBConnection() throws ClassNotFoundException, SQLException
	{
		Connection conn=null;
		Class.forName("oracle.jdbc.driver.OraceDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");
		return conn;
	}
}
