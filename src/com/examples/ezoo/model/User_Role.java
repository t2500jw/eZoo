package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role", schema="public")
public class User_Role {	
	
	@Id
	@Column(name="username")
	String username = "";
	@Column(name="role")
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
