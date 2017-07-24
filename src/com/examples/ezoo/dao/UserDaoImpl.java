package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.FeedingSchedule;
import com.examples.ezoo.model.Users;



public class UserDaoImpl implements UserDAO {

	@Override
	public String createUserAccount(String username, String password1, String password2) {
		
		if(!password1.equals(password2)) {
			return "Passwords do not match!";
		}
		else if(!checkLength(username)) {
			return "Username must be at least 8 characters long!";
		}
		else if (!checkLength(password1)) {
			return "Password must be at least 8 characters long!";
		}		
		else if(!checkPasswordRequirements(password1)) {
			return "Password must have at least 2 numeric characters!";
		}
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO public.\"USERS\"(\"Username\",\"Password\") VALUES (?, ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password1);
			int success = stmt.executeUpdate();	
			
			sql = "INSERT INTO public.\"USER_ROLE\"(username, role) VALUES (?, ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, "Customer");
			success = stmt.executeUpdate();
			
			return "User created successfully!";
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
			return "There was a problem creating the user!";
		}		
		
		
	}

	@Override
	public String deleteUserAccount(String username) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
				connection = DAOUtilities.getConnection();
				String deleteUserFromUserRoleSql = "DELETE FROM public.\"USER_ROLE\" WHERE \"username\" = ?";
				String deleteUserSql = "DELETE FROM public.\"USERS\" WHERE \"Username\" = ?";
				
				stmt = connection.prepareStatement(deleteUserFromUserRoleSql);
				stmt.setString(1, username);		
				stmt.executeUpdate();
				
				stmt = connection.prepareStatement(deleteUserSql);
				stmt.setString(1, username);
				stmt.executeUpdate();
		
		return "User Successfully Deleted!";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Problem Deleting User!";
		}
	}

	@Override
	public String updateUserPassword(String username, String password1, String password2) {
		
		if(!password1.equals(password2)) {
			return "Passwords do not match!";
		}
		else if(!checkLength(username)) {
			return "Username must be at least 8 characters long!";
		}
		else if (!checkLength(password1)) {
			return "Password must be at least 8 characters long!";
		}		
		else if(!checkPasswordRequirements(password1)) {
			return "Password must have at least 2 numeric characters!";
		}
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
				connection = DAOUtilities.getConnection();
				String sql = "UPDATE public.\"USERS\" SET \"Password\" = ? WHERE \"Username\" = ?";			
				
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, password1);	
				stmt.setString(2, username);
				stmt.executeUpdate();					
		
		return "Password successfully changed!";
		
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Problem Changing Password!";
		}	
		
	}
	
	@Override
	public List<Users> getUsers() {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		List<Users> users = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM public.\"USERS\"";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Users u = new Users();
				u.setUsername(rs.getString("Username"));			
				users.add(u);			
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return users;
	}
	
	private boolean checkLength(String txt) {
		
		if(txt.length() < 8) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	private boolean checkPasswordRequirements(String pass) {
		
		int cnt = 0;
		
		for(int i = 0;i < pass.length();i++) {
			
			if(Character.isDigit(pass.charAt(i))) {
				cnt++;
			}
			
		}
		
		if(cnt>=2) {
			return true;
		}
		else {
			return false;
		}
		
	}

	

}
