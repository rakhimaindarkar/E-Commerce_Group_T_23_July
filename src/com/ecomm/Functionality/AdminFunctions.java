package com.ecomm.Functionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ecomm.DBOperation.Orders_DBO;
import com.ecomm.DBOperation.Product_DBO;
import com.ecomm.DBOperation.User_DBO;
import com.ecomm.Product.Products;
import com.ecomm.User.Users;

public class AdminFunctions {

	public static void main(String[] args) {
		AdminFunctions af =new AdminFunctions();
		//af.addProduct();
		//af.removeProduct(4);
		af.removeUser(4);
	}
	
	private void displayAllProduct() {
		Product_DBO dbo= new Product_DBO();
		List<Products> list	=dbo.displayAllProduct();
		System.out.println("List of all Products:");
		System.out.println("ItemNo:"+"\t"+"ProductID:"+"\t"+"Product Name:\t"+"Quantity:\t"+"Product's Price:\t"+"Product Discription:\t");
		for(int i=0;i<list.size();i++) {

			System.out.println(i+1+"\t\t"+list.get(i).getId()+"\t\t"+list.get(i).getName()+"\t\t"+list.get(i).getQuantity()+"\t\t"+list.get(i).getPrice()+"\t\t"+list.get(i).getDescription()+"\t\t");
		}
			
}
	public void admin_function(Users user) {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Welcome Admin,"+user.getUserName());
		while(true) {
		
			System.out.println("1. View all Product");
			System.out.println("2. Add product");
			System.out.println("3. Delete Product");
			System.out.println("4. View All User");
			System.out.println("5. Delete user");
			System.out.println("6. Order of user");
			System.out.println("7. Log-out");
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("Enter Your choice:");
			int i=sc.nextInt();
			switch(i){
				case 1:
					System.out.println("List of all Products");
					displayAllProduct();
					break;
				case 2:
					System.out.println("Add Product");
					addProduct();		
					break;
				case 3:
					System.out.println("Delete Product");
					System.out.println("Enter id to be deleted");
					int id2=sc.nextInt();
					removeProduct(id2);
					break;
				case 4:
					System.out.println("Below is All User");
					displayAllUsers();
					
					break;
				case 5:
					System.out.println("Delete user");
					System.out.println("Enter used id :");
					int id=sc.nextInt();
					removeUser(id);
					
					break;
				case 6:
					System.out.println("Order of user");
					System.out.println("Enter used id :");
					int id1=sc.nextInt();
					displayUsersOrder(id1);
					break;
				case 7:
					System.out.println("Sucessfully logged out");
					return;
				default:
					System.out.println("Invalid choice!!!!!");
					
					break;
			}
			System.out.println("-----------------------------------------------------------------");	
		}
		
	}

	private void displayUsersOrder(int id) {
		Users user = new Users();
		Orders_DBO dbo = new Orders_DBO();
		user.setId(id);
		dbo.yourorder(user);
		System.out.println("-----------------------------------------------------------------");
	}

	private void removeUser(int u_id) {
		
		User_DBO dbo =new User_DBO();
		if(dbo.deleteUser(u_id)) {
			System.out.println("User is Removed Sucessfully");
		}else {
			System.out.println("User is not Present in the db");
		}
		
	}

	private void displayAllUsers() {
		User_DBO dbo = new User_DBO();
		List<Users> list = new ArrayList<Users>();
		list=dbo.getallUser();
		System.out.println("All Registered Users are :");
		System.out.println("UserID:"+"\t"+"User Name:\t"+"User Password:\t"+"Address:\t"+"User Type:");
			
		for(Users u: list) {
			System.out.println(u.getId()+"\t\t"+u.getUserName()+"\t\t"+"*".repeat(u.getPassword().length())+"\t\t"+u.getAddress()+"\t\t"+u.getUserType()+"\t\t");
		}
			
		}
		
	private void removeProduct(int id) {
		boolean flag=false;
		Product_DBO dbo=new Product_DBO();
		flag=dbo.deleteProduct(id);
		if(flag==true) {
		System.out.println("Deletion sucessful");
		}else {
			System.out.println("Product is not present in DB ...already removed");
		}
		displayAllProduct();
	}

	private void addProduct() {
		Product_DBO dbo =new Product_DBO();
		
		System.out.println("enter new product :");
		System.out.println("Enter products Details:");
		Scanner sc =new Scanner(System.in);
		
		System.out.println("enter name of product");
		String name=sc.nextLine();
		
		System.out.println("enter description of product");	
		String description=sc.nextLine();
		
		System.out.println("enter Quantity of product");
		int quantity=sc.nextInt();
		
		System.out.println("enter price of product");
		double price=sc.nextDouble();
		
		Products product = dbo.insertProduct(name, description, quantity, price);
		if(product!=null) {
			System.out.println("Successfully inserted");
		}else {
			System.out.println(" Insertion is failed");
		}
		displayAllProduct();
	
	}

	
}
