package com.ecomm.User;

public class Users {
	private int id;
	private	String userName;
	private String password;
	private String address;
	private String userType;
	
	
	public Users() {
		super();
	}
	
	
	public Users(int id,String userName, String password, String address,String type) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.userType=type;
	}

	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


}
