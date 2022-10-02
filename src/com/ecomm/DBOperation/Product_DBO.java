package com.ecomm.DBOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.Product.Products;

public class Product_DBO {
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
			System.out.println("Level 5 : Connection Fail");
			e.printStackTrace();
		}
		return con;
		
	}
	
	//display all product
	public List<Products> displayAllProduct() {
		List<Products> products= new ArrayList<Products>();
		String q2="select * from Product order by name asc";
		Connection con=getConnection();
		try {
			PreparedStatement prs =con.prepareStatement(q2);
			
			ResultSet rs=prs.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String pName=rs.getString("name");
				String pdescription=rs.getString("description");
				int pQuantity = rs.getInt("quantity");
				double pPrice = rs.getInt("price");
				//System.out.println("TEST "+pName);
				products.add(new Products(id,pName,pdescription,pQuantity,pPrice)); 
			}
		} catch (SQLException e) {
			System.out.println("Level 7: PRodcut Validation level is failed");
			//e.printStackTrace();
		}
		return products;
	}
	
	//Insert new product
	
	public Products insertProduct(String name, String description, int quantity,double price) {
		
		Products product =new Products();
		String q2="insert into product (name, description, quantity,price) values(?,?,?,?)";
		
		//Step 1 : Get Connection;
		Connection con=getConnection();
		try {
		
			//Step 2 : Prepare the Query;
			PreparedStatement prs =con.prepareStatement(q2);
			
			//Step 3 : Add the values;
			prs.setString(1, name);
			prs.setString(2, description);
			prs.setInt(3, quantity);
			prs.setDouble(4, price);
			
			
			//Test the query on console
			//System.out.println(prs);
			
			//Step 4 : Execute the update Query
			prs.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Level 8: Product insertion level is failed");
			//e.printStackTrace();
		}
		
		
		return product;
		
	}
	
	//delete product admin can do so
	
	public boolean deleteProduct(int id) {
		boolean result = false;
		String q2="delete from product where id=?";
		
		//Step 1 : Get Connection;
		Connection con=getConnection();
		try {
		
			//Step 2 : Prepare the Query;
			PreparedStatement prs =con.prepareStatement(q2);
			
			//Step 3 : Add the values;
			prs.setInt(1, id);
			
			//Test the query on console
			//System.out.println(prs);			
			
			//Step 4 : Execute the update Query
			 result = prs.executeUpdate() > 0;    // executeUpdate() return no of rows affected
			 System.out.println(result);
			
		} catch (SQLException e) {
			System.out.println("Level 9: product deletion level is failed");
			//e.printStackTrace();
		}	
		
		return result;
	}
	
	public Products getProduct(int id) {
		Products products = null;
		String q2="select * from product where id=?";
		
		//Step 1 : Get Connection;
		Connection con=getConnection();
		try {
		
			//Step 2 : Prepare the Query;
			PreparedStatement prs =con.prepareStatement(q2);
			
			//Step 3 : Add the values;
			prs.setInt(1, id);
			
			//Test the query on console
			//System.out.println(prs);			
			
			//Step 4 : Execute the update Query
			 ResultSet rs = prs.executeQuery();    // executeUpdate() return no of rows affected
			 
			 while(rs.next()) {
					int product_id=rs.getInt("id");
					String pName=rs.getString("name");
					String pdescription=rs.getString("description");
					int pQuantity = rs.getInt("quantity");
					double pPrice = rs.getInt("price");
					//System.out.println("TEST "+pName);
					products = new Products(product_id,pName,pdescription,pQuantity,pPrice); 
				}
			
		} catch (SQLException e) {
			System.out.println("Level 10: product deletion level is failed");
			//e.printStackTrace();
		}	
		
		return products;
	}
	
	
	public static void main(String[] args) {
		Product_DBO op = new Product_DBO();
//		op.insertProduct("Iron", "philips", 4, 5000);
//		
//		
//		List<Products> list= op.displayAllProduct();				//}unit testing
//		System.out.println("Testing :Username :"+list.get(2).getName()+ "\t "+"List size:"+list.size()); //4
//
//		op.deleteProduct(5);
//		
//		list= op.displayAllProduct();
//		System.out.println("Testing :Username :"+list.get(2).getName()+ "\t "+"List size:"+list.size()); //3
		System.out.println(op.getProduct(1).getName());
		

	}

}
