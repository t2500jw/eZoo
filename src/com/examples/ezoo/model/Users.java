package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "users", schema="public")
public class Users {	
	
	@Id
	@Column(name="username")
	String username = "";
	@Column(name="password")
	String password = "";
	
	public Users() {
		
	}

	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	

}
