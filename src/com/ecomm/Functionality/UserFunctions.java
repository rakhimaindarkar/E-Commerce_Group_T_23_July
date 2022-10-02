package com.ecomm.Functionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ecomm.DBOperation.Orders_DBO;
import com.ecomm.DBOperation.Product_DBO;
import com.ecomm.Order.Orders;
import com.ecomm.Product.Products;
import com.ecomm.User.Users;

public class UserFunctions {
	ArrayList<Orders> cart = new ArrayList<Orders>();
	
	//Unit box testing //only for testing
	public static void main(String[] args) {
		UserFunctions uf = new UserFunctions();
		uf.addToCart(1);
		uf.displayCart();
		uf.addToCart(1);
		uf.addToCart(1);
		
		uf.removeFromCart(1);
		uf.removeFromCart(1);
		
		uf.displayCart();
		uf.removeFromCart(1);
		
		uf.displayCart();

	}
	
	private void confirmOrder(Users user) {
		Orders_DBO dbo = new Orders_DBO();
		dbo.placeorder(cart, user);
		cart.removeAll(cart);
		displayCart();
	}

	private void yourOrder(Users user) {
		Orders_DBO dbo = new Orders_DBO();
		dbo.yourorder(user);
		System.out.println("-----------------------------------------------------------------");
	}
	
	private  void displayAllProduct() {
		Product_DBO dbo= new Product_DBO();
		List<Products> list	=dbo.displayAllProduct();
		System.out.println("List of all Products:");
		System.out.println("ItemNo:"+"\t"+"ProductID:"+"\t"+"Product Name:\t"+"Product Discription:\t"+"Quantity:\t"+"Product's Price:\t");
		for(int i=0;i<list.size();i++) {

			System.out.println(i+1+"\t\t"+list.get(i).getId()+"\t\t"+list.get(i).getName()+"\t\t"+list.get(i).getDescription()+"\t\t"+list.get(i).getQuantity()+"\t\t"+list.get(i).getPrice());
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	//add product to cart
	private void addToCart(int id) {
		Product_DBO dbo = new Product_DBO();
		Products product = dbo.getProduct(id);
		Orders order = new Orders();
		
		order.setProduct(product);
		
		if(cart.isEmpty()) {
			order.setItemNo(1);
			order.setNoOfProduct(1);
			cart.add(order);
		}else {
			int flag=0,q=0;
			
			//check in cart if found 1 else 0
			for(int i=0; i< cart.size();i++) {
				if(cart.get(i).getProduct().getId()==id) {
					q=i;
					flag=1;
					break;
				}
			}
			
			//Adding product if flag 1 then increase the quantity else insert new order with quantity one
			if(flag == 1) {
				cart.get(q).setNoOfProduct(cart.get(q).getNoOfProduct()+1);
			}else {
				order.setNoOfProduct(1);
				order.setItemNo(cart.size()+1);
				cart.add(order);
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	private void removeFromCart(int id) {
		if(cart.isEmpty()) {
			System.out.println("Cart is Empty");
		}else {
			int flag=0,q=0;
			
			//check in cart if found 1 else 0
			for(int i=0; i< cart.size();i++) {
				if(cart.get(i).getProduct().getId()==id) {
					q=i;
					flag=1;
					break;
				}
			}
			
			//delete from cart
			if(flag == 1) {
				cart.get(q).setNoOfProduct(cart.get(q).getNoOfProduct()-1);
				if(cart.get(q).getNoOfProduct()<=0) {
					cart.remove(q);
				}
			}

		}	
		System.out.println("-----------------------------------------------------------------");
	}
	
	private void displayCart() {
		double Total = 0;
		if(cart.isEmpty()) {
			System.out.println("Cart is Empty");
		}else {
			System.out.println("Items inCart");
			for(int i=0; i< cart.size();i++) {
				System.out.println("Item No :"+cart.get(i).getItemNo());
				System.out.println("Product Name :"+cart.get(i).getProduct().getName());
				System.out.println("Quantity :"+cart.get(i).getNoOfProduct());
				double price = cart.get(i).getNoOfProduct()*cart.get(i).getProduct().getPrice();
				Total = Total + price;
				System.out.println("Price  :"+price);
			}
			System.out.println("Total Amount :"+Total);
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	//Choice Box
	public void user_function(Users user) {
		Scanner sc= new Scanner(System.in);
		int i;
		System.out.println("Welcome, "+user.getUserName());
		
		while(true) {
			
			System.out.println("1. View all Product");
			System.out.println("2. Add Product to cart");
			System.out.println("3. Remove product from cart");
			System.out.println("4. View cart");
			System.out.println("5. Confirm order");
			System.out.println("6. Ordered list of product");
			System.out.println("7. Log-out");
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("Enter Your choice:");
			i=sc.nextInt();
			switch(i){
				case 1:
					System.out.println("List of Product");
					displayAllProduct();
					
					break;
				case 2:
					System.out.println("Adding Cart");
					System.out.println("Enter product choice id :");
					int id = sc.nextInt();
					addToCart(id);
					displayCart();
					break;
				case 3:
					System.out.println("Remove from CArt");
					System.out.println("Enter product id to remove :");
					int id_1 = sc.nextInt();
					removeFromCart(id_1);
					displayCart();
					break;
				case 4:
					System.out.println("Display Cart");
					displayCart();
					break;
				case 5:
					System.out.println("Confirm your Order");
					confirmOrder(user);
					break;
				case 6:
					System.out.println("Your Orders");
					yourOrder(user);
					break;
				case 7:
					System.out.println("Thank you for Shoping with us!!!!!");
					return;
				default:
					System.out.println("Invalid choice!!!!");
					break;
			}
			System.out.println("-----------------------------------------------------------------");
			sc.close();
		}
		
	}

}
