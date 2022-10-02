package com.ecomm.DBOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecomm.User.Users;

public class User_DBO {
	private String url="jdbc:mysql://localhost:3306/rakhi";
	private String username="root";
	private String password="";
	
public Connection getConnection() {
	Connection con=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url, username, password);
		
	}catch(Exception e)
	{
		System.out.println("Level 1 : Connection Fail");
		e.printStackTrace();
	}
	return con;
	
}

//Get User;
public Users getUser(String userName, String password) {
	Users user = null;
	String q2="select * from users where name=? and password=?;";
	Connection con=getConnection();
	try {
		PreparedStatement prs =con.prepareStatement(q2);
		prs.setString(1, userName);
		prs.setString(2, password);
		
		ResultSet rs=prs.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("id");
			String username=rs.getString("name");
			String Password=rs.getString("password");
			String address = rs.getString("address");
			String userType1 = rs.getString("user_type");
			System.out.println("User_Type :"+ userType1);
			user = new Users(id, username, Password, address, userType1);
		}
	} catch (SQLException e) {
		System.out.println("Level 2: User Validation level is failed");
		//e.printStackTrace();
	}
	return user;
}

//Inserting the users;
public void insertUser(String userName, String password,String address) {
	String q2="insert into Users (name, password, address,user_type) values(?,?,?,'non-admin')";
	
	//Step 1 : Get Connection;
	Connection con=getConnection();
	try {
	
		//Step 2 : Prepare the Query;
		PreparedStatement prs =con.prepareStatement(q2);
		
		//Step 3 : Add the values;
		prs.setString(1, userName);
		prs.setString(2, password);
		prs.setString(3, address);
		
		//Test the query on console
		//System.out.println(prs);
		
		//Step 4 : Execute the update Query
		prs.executeUpdate();
		
	} catch (SQLException e) {
		System.out.println("Level 3: User insertion level is failed");
		//e.printStackTrace();
	}
}

//Delete user
public boolean deleteUser(int id) {   //as delete statement work on primary key only 
	boolean result = false;
	String q2="delete from Users where id=?";
	
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
		System.out.println("Level 4: User deletion level is failed");
		//e.printStackTrace();
	}
	return result;
}

//Display All users
@SuppressWarnings("null")
public List<Users> getallUser() {
	List<Users> user= new ArrayList<Users>();
	String q2="select * from users";
	Connection con=getConnection();
	try {
		PreparedStatement prs =con.prepareStatement(q2);
		
		ResultSet rs=prs.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String username=rs.getString("name");
			String Password=rs.getString("password");
			String address = rs.getString("address");
			String type = rs.getString("user_type");
		
			user.add(new Users(id,username,Password,address,type)); 
		}
	} catch (SQLException e) {
		System.out.println("Level 5: User Validation level is failed");
		//e.printStackTrace();
	}
	return user;
}

public static void main(String Args[]) {
	User_DBO op = new User_DBO();
												
	List<Users> list= op.getallUser();				//}unit testing
	System.out.println("Testing :Username :"+list.get(0).getUserName()+"List size:"+list.size());
}

}
