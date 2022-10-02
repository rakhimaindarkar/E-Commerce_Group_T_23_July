package com.ecomm.DBOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.Order.Orders;
import com.ecomm.Product.Products;
import com.ecomm.User.Users;

public class Orders_DBO {
	
	private String url="jdbc:mysql://localhost:3306/rakhi";
	private String username="root";
	private String password="";

	//Get the connection
	public Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
			
		}catch(Exception e)
		{
			System.out.println("Level 11 : Connection Fail");
			//e.printStackTrace();
		}
		return con;
		
	}
	
	public void placeorder(List<Orders> orders,Users user) {
			
			String q2="insert into orders(user_id,product_id,quantity,price,delivery_date) Values(?,?,?,?, current_date()+2)";
			String q3="update product set quantity = quantity - ? where id=?";
			//Step 1 : Get Connection;
			Connection con=getConnection();
			try {
			
				//Step 2 : Prepare the Query;
				PreparedStatement prs =con.prepareStatement(q2);
				PreparedStatement prs1 =con.prepareStatement(q3);
				
				for(int i=0;i<orders.size();i++) {
					//System.out.println("TEST i:"+i);
					Orders order = orders.get(i);
					int quantity = orders.get(i).getNoOfProduct();
					double price = orders.get(i).getProduct().getPrice()*quantity;
					
//					System.out.println("Quantity : "+quantity);
//					System.out.println("p PRice o: "+order.getProduct().getPrice());
//					System.out.println("PRice o: "+price);
					
					prs.setInt(1, user.getId());
					prs.setInt(2, order.getProduct().getId());
					prs.setInt(3, quantity);
					prs.setDouble(4, price);
					
					//System.out.println(prs);
					
					//order table made insert kela
					if(prs.executeUpdate() > 0) {
						System.out.println("Your order is placed");
					}else {
						System.out.println("Your Order cannot be placed");
					}
					
					
					// quantity update keli product table made;
					prs1.setInt(1, quantity);
					prs1.setInt(2, order.getProduct().getId());
					
					prs1.executeUpdate();
					
					
				}
				
			} catch (SQLException e) {
				System.out.println("Level 12: Order insertion level is failed");
				//e.printStackTrace();
			}
	}
	
	public void yourorder(Users user) {
		String q3="select users.name as u_name, product.name as p_name,product.price as price,sum(orders.quantity) as quantity,sum(orders.price) as amount from orders inner join product on orders.product_id = product.id inner join users on orders.user_id=users.id where users.id=? group by product.id order by p_name asc";
		//Step 1 : Get Connection;
		Connection con=getConnection();

		try {
			int flag=0;
			PreparedStatement prs = con.prepareStatement(q3);
			System.out.println("User id :"+user.getId());
			prs.setInt(1, user.getId());
			
			ResultSet rs=prs.executeQuery();
			
			while(rs.next()) {
				System.out.println("Your Orders below :");
				System.out.println("User Name : "+rs.getString("u_name"));
				System.out.println("Product Name : "+rs.getString("p_name"));
				System.out.println("Product Price : "+rs.getDouble("price"));
				System.out.println("Quantity : "+rs.getInt("quantity"));
				System.out.println("Total Amount : "+rs.getDouble("amount"));
				flag = flag +1;
			}
			
			if(flag==0) {
				System.out.println("You don't have any order placed recently");
				System.out.println("Welcome to Shoping with us !!!!");
			}
			
		} catch (SQLException e) {
			System.out.println("Level 14: Failed to get your order, Pls check again you will difinatly find it");
			//e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Orders_DBO dbo = new Orders_DBO();
		Orders order = new Orders();
		Products product = new Products();
		Users user = new Users();
		
		user.setId(1);
		
		product.setId(2);
		product.setPrice(5000);
		order.setItemNo(1);
		order.setNoOfProduct(1);
		order.setProduct(product);
		
		List<Orders> o = new ArrayList<Orders>();
		//o.add(order);
		
		System.out.println("Order size :"+ o.size());
		
		//dbo.placeorder(o, user);
		
		dbo.yourorder(user);
	}

}
