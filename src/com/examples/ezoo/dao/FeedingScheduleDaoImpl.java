package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDaoImpl implements FeedingScheduleDAO {

	public FeedingScheduleDaoImpl() {
		
	}

	@Override
	public int AddFeedingSchedule(FeedingSchedule schedule) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;	
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();			
			String sql = "INSERT INTO public.\"Feeding_Schedules\" (\"time\", recurrence, food, notes) VALUES (?,?,?,?)";
			
			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);						
			//stmt.setLong(1, schedule.getSchedule_ID());
			stmt.setString(1, schedule.getTime());
			stmt.setInt(2, schedule.getRecurrence());
			stmt.setString(3, schedule.getFood());
			stmt.setString(4, schedule.getNotes());
			
			success = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Insert feeding schedule failed: " + schedule);
		}
		
		return success;
	}

	@Override
	public int DeleteFeedingSchedule(Integer id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "Update public.animals SET \"feedingSchedule\" = ? WHERE \"feedingSchedule\" = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, 99);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			
			
			sql = "DELETE FROM \"Feeding_Schedules\" WHERE \"schedule_ID\" = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
		return success;
		
	}

	@Override
	public List<FeedingSchedule> GetFeedingSchedules() {
		Connection connection = null;
		PreparedStatement stmt = null;
		List<FeedingSchedule> schedules = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM public.\"Feeding_Schedules\"";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				FeedingSchedule f = new FeedingSchedule();
				f.setSchedule_ID(rs.getLong("schedule_ID"));
				f.setTime(rs.getString("time"));
				f.setRecurrence(rs.getInt("recurrence"));
				f.setFood(rs.getString("food"));
				f.setNotes(rs.getString("notes"));
				
				schedules.add(f);
				
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return schedules;
	}

	@Override
	public FeedingSchedule GetFeedingScheduleForAnAnimal(int animalID) {
		Connection connection = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		FeedingSchedule f = new FeedingSchedule();
		int sql1Result = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql1 = "SELECT \"feedingSchedule\" FROM animals WHERE animalid = ?";				
			stmt1 = connection.prepareStatement(sql1);
			stmt1.setLong(1, animalID);
			sql1Result = stmt1.executeUpdate();
			
			String sql2 = "SELECT * FROM \"Feeding_Schedules\" WHERE \"schedule_ID\" = " + sql1Result;
			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs = stmt2.executeQuery();
			
			f.setSchedule_ID(rs.getLong("scheduleID"));
			f.setTime(rs.getString("time"));
			f.setRecurrence(rs.getInt("recurrence"));
			f.setFood(rs.getString("food"));
			f.setNotes(rs.getString("notes"));
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return f;
	}

	@Override
	public int AssignFeedingSchedule(long feedingScheduleID, String animalName) {
		Connection connection = null;
		PreparedStatement stmt = null;	
		
		String sql = "";
		int result = 0;
		
		
		try {
			connection = DAOUtilities.getConnection();			
			
			sql = "UPDATE public.animals SET \"feedingSchedule\" = ? WHERE name = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, feedingScheduleID);
			stmt.setString(2, animalName);
			
			//Debugging
			System.out.println(stmt.toString());
			
			result = stmt.executeUpdate();			
			
	
		} catch (SQLException e) {			
			e.printStackTrace();
			return result;
		}		
		
		
		return result;
		
	}

	@Override
	public void UpdateFeedingSchedule(FeedingSchedule schedule) {
		
		Connection connection = null;
		PreparedStatement stmt = null;		
	
		
		
		try {
			connection = DAOUtilities.getConnection();			
			
			String sql = "UPDATE public.\"Feeding_Schedules\" SET recurrence=?, food=?, notes=?, \"time\"=? WHERE \"schedule_ID\" = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, schedule.getRecurrence());
			stmt.setString(2, schedule.getFood());
			stmt.setString(3, schedule.getNotes());
			stmt.setString(4, schedule.getTime());
			stmt.setLong(5, schedule.getSchedule_ID());	
			
			stmt.executeUpdate();			
			
	
		} catch (SQLException e) {			
			e.printStackTrace();			
		}		
		
		
	}

	@Override
	public FeedingSchedule GetFeedingSchedule(long scheduleID) {
		
		FeedingSchedule f = new FeedingSchedule();
		long id = 0L;
		id = scheduleID;
		
		
		Connection connection = null;
		PreparedStatement stmt = null;			
		
		try {
			connection = DAOUtilities.getConnection();			
			
			String sql = "SELECT recurrence, food, notes, \"time\" FROM public.\"Feeding_Schedules\" WHERE \"schedule_ID\" = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, scheduleID);			
			
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			f.setFood(rs.getString("food"));
			f.setNotes(rs.getString("notes"));
			f.setRecurrence(rs.getInt("recurrence"));
			f.setTime(rs.getString("time"));
			f.setSchedule_ID(id);					
			
			
	
		} catch (SQLException e) {			
			e.printStackTrace();
			
		}
		return f;		
		
	}

	

}
