package com.wipro.sales.main;
import java.util.*;
import com.wipro.sales.bean.*;
import com.wipro.sales.service.*;
import java.sql.SQLException;
import java.text.ParseException;

public class SalesApplication
{
	public static void main(String args[]) throws ClassNotFoundException, SQLException, ParseException
	{
		Scanner sc=new Scanner(System.in);
		Administrator admin=new Administrator();
		int n=0;
		do {
		System.out.println("Enter your choice: ");
		System.out.println("1. Insert Stock");
		System.out.println("2. Delete Stock");
		System.out.println("3. Insert Sales");
		System.out.println("4. View Sales Report");
		n=sc.nextInt();
		
		switch(n)
		{
		case 1:Product pro=new Product();
		System.out.print("Enter product ID: ");
		pro.setProductID(sc.next());
		System.out.print("Enter product name: ");
		pro.setProductName(sc.next());
		System.out.print("Enter quantity on hand: ");
		pro.setQuantityOnHand(sc.nextInt());
		sc.nextLine();
		System.out.print("Enter product unit price: ");
		pro.setProductUnitPrice(sc.nextDouble());
		System.out.print("Enter product reorder level: ");
		pro.setReorderLevel(sc.nextInt());
		sc.nextLine();
		admin.insertStock(pro);
		break;
		
		
		case 2:
			System.out.print("Enter product id to be deleted: ");
			String remove=sc.next();
			remove=admin.deleteStock(remove);
			if(remove!=null)
				System.out.println(remove+" removed successfully!!!");
			break;
			
			
		case 3:
			Sales s= new Sales();
			System.out.print("Enter sales id: ");
			s.setSalesID(sc.next());
			System.out.print("Enter date (dd-mm-yyyy): ");
			String sDate = sc.next(); 
			java.util.Date utilDate=new java.util.Date(); 
			s.setSalesDate(utilDate);
			System.out.print("Enter product id: ");
			s.setProductID(sc.next());
			System.out.print("Enter quantity sold: ");
			s.setQuantitySold(sc.nextInt());
			sc.nextLine();
			System.out.print("Enter sales price per unit: ");
			s.setSalesPricePerUnit(sc.nextDouble());
			admin.insertSales(s);
			break;
			
			
			
		case 4:
			ArrayList<SalesReport> list=new ArrayList<>();
			
			list=admin.getSalesReport();
			break;
		default: System.out.println("Exiting the application....");
		}
		}while(n>=1 && n<=4);
	}
}
