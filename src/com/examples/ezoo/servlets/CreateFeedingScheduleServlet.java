package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AddFeedingScheduleServlet
 */
@WebServlet("/addFeedingSchedule")
public class CreateFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		//We MUST convert to a Long since parameters are always Strings
		
		//long id = Long.parseLong(request.getParameter("id"));
		String time = request.getParameter("time");
		int recurrence = Integer.parseInt(request.getParameter("recurrence"));
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		
		//Create an Feeding Schedule object from the parameters
		FeedingSchedule schedule = new FeedingSchedule(
				//id,
				time,
				recurrence,
				food,
				notes);
		
		//Call DAO method		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.AddFeedingSchedule(schedule);
			request.getSession().setAttribute("message", "Feeding Schedule successfully created");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");


		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "Id of " + schedule.getSchedule_ID() + " is already in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
			
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);

		}
	}

}
