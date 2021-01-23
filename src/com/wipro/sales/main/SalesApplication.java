package com.wipro.sales.main;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import com.wipro.sales.bean.*;
import com.wipro.sales.service.Administrator;

public class SalesApplication {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char choice = 'y';
		
		System.out.println("Which operation do you wish to perform?");
		
		do
		{
			System.out.println("1. Insert Stock \n2. Delete Stock \n3. Insert Sales\n4. View Sales Report \n");
			System.out.print("Enter the operation you want to perform : ");
			int op = Integer.parseInt(br.readLine());
			switch (op)
			{
			case 1:
				Product stockobj = new Product();
				System.out.print("\nEnter Product Name : ");
				String productName = br.readLine();
				System.out.print("\nEnter Quantity On Hand : ");
				int quantityOnHand = Integer.parseInt(br.readLine());
				System.out.print("\nEnter Reorder Level : ");
				int reorderLevel = Integer.parseInt(br.readLine());
				System.out.print("\nEnter Product Unit Price : ");
				double productUnitPrice = 0;
				try {
					productUnitPrice = Double.parseDouble(br.readLine());
				} catch (Exception e) {
					e.printStackTrace();
				} 
				System.out.println();
				stockobj.setProductName(productName);
				stockobj.setQuantityOnHand(quantityOnHand);
				stockobj.setReorderLevel(reorderLevel);
				stockobj.setProductUnitPrice(productUnitPrice);
				System.out.println(Administrator.insertStock(stockobj));
				break;
			case 2:
				System.out.print("Enter the Product ID of the Stock you want to delete : ");
				String proID = br.readLine();
				System.out.println(Administrator.deleteStock(proID));
				break;
			case 3:
				System.out.print("\nEnter the product ID : ");
				String pID = br.readLine();
				System.out.print("\nEnter the date : ");
				String date = br.readLine();
				Date salesDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
				System.out.print("\nEnter Quantity Sold : ");
				int quantitySold = Integer.parseInt(br.readLine());
				System.out.print("\nEnter Sales Per Unit Price : ");
				double salesPricePerUnit = 0;
				try {
					salesPricePerUnit = Double.parseDouble(br.readLine());
				} catch (Exception e) {
					e.printStackTrace();
				} 
				System.out.println();
				Sales salesobj = new Sales();
				salesobj.setProductID(pID);
				salesobj.setSalesDate(salesDate);
				salesobj.setQuantitySold(quantitySold);
				salesobj.setSalesPricePerUnit(salesPricePerUnit);
				System.out.println(Administrator.insertSales(salesobj));
				break;
			case 4:
				System.out.println("Following is the Sales Report : \n");
				ArrayList<SalesReport> al = Administrator.getSalesReport();
				for (SalesReport e : al)
					System.out.println(e);
				break;
			default :
				System.out.println("\nSelect from choices listed below.\n");
				continue;
			}
			System.out.print("\nDo you wish to perform any other operation? ");
			choice = br.readLine().charAt(0);
		} while (choice == 'y' || choice == 'Y');
		
		System.out.println("\nOK, Bye!");
		
		br.close();

	}

}
