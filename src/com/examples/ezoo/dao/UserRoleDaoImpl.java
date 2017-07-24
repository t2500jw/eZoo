package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.User_Role;
import com.examples.ezoo.model.Users;

public class UserRoleDaoImpl implements UserRoleDAO {

	@Override
	public List<User_Role> GetUserRoles() {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		List<User_Role> users = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM public.\"USER_ROLE\"";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				User_Role u = new User_Role();
				u.setUsername(rs.getString("username"));
				u.setRole(rs.getString("role"));
				users.add(u);			
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return users;
	}

	@Override
	public boolean SetUserRole(String username, String role) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
