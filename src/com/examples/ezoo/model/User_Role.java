package com.examples.ezoo.model;

public class User_Role {
	
	String username = "";
	String role = "";
	
	public User_Role() {
		
	}

	public User_Role(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
