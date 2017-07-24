package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Users;

public interface UserDAO {
	
	
	String createUserAccount(String username, String password1, String password2);
	String deleteUserAccount(String username);
	String updateUserPassword(String username, String password1, String password2);
	List<Users> getUsers();

}
