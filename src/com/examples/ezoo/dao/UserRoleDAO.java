package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.User_Role;

public interface UserRoleDAO {
	
	List<User_Role> GetUserRoles();
	boolean SetUserRole(String username, String role);

}
