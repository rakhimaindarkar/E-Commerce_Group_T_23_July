package com.ecomm.Main;


import java.util.Date;
import java.util.Scanner;
import com.ecomm.DBOperation.User_DBO;
import com.ecomm.Functionality.AdminFunctions;
import com.ecomm.Functionality.UserFunctions;
import com.ecomm.User.Users;

public class UI {

	public static void main(String[] args) {
		//REquired object
		User_DBO DBO = new User_DBO();
		Date date = new Date();
		Scanner sc=new Scanner(System.in);
		Users user = new Users();
		AdminFunctions adminfunc = new AdminFunctions();
		UserFunctions userfunc =new UserFunctions();
		
		while(true){
			System.out.println("Openingin Project...");
			System.out.println(date);
			//Thread.sleep(100);
			
			System.out.println("---------------E-Commerse-------------------");
			System.out.println("Enter your username : ");
			String userName = sc.nextLine();
			
			System.out.println("Enter your Password");
			String password=sc.nextLine();
			
			user = DBO.getUser(userName, password);
			if(user == null) {
				System.out.println("Incorrect username or Password");
				System.out.println("Your are not Registor in System!!!");
				System.out.println("Do you want to re-try Y/N");
				char chooice = sc.next().charAt(0);
				if(chooice=='Y') {
					continue;
				}
				
				System.out.println("Registration System!!!");
				System.out.println("Pls Register your self");
				System.out.println("Enter user name");
				String un = sc.nextLine();
				
				System.out.println("Enter Password");
				String ps = sc.nextLine();
				
				System.out.println("Enter Address");
				String ad = sc.nextLine();
				DBO.insertUser(un, ps,ad);
				
				System.out.println("Successfully Registered");
				System.out.println("Pls login now");
				continue;
			}else {
//				System.out.println("UserType : "+user.getUserType());
//				System.out.println("Condition Check : "+user.getUserType().equals("admin"));
				if(user.getUserType().equals("admin")) {
						adminfunc.admin_function(user);
					
				}else {
					userfunc.user_function(user);
			}
			}
			sc.close();
		}
	}

}
	

