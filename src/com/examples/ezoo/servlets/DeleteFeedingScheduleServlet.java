package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;


/**
 * Servlet implementation class DeleteFeedingScheduleServlet
 */
@WebServlet(description = "This servlet is the main interface into the Feeding Schedule", urlPatterns = { "/deleteFeedingSchedule" })
public class DeleteFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Animals from the Database
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> schedules = dao.GetFeedingSchedules();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("schedules", schedules);		
		
		
		request.getRequestDispatcher("deleteSchedulesHome.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		//Call DAO method		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.DeleteFeedingSchedule(id);
			request.getSession().setAttribute("message", "Deleted Schedule successfully");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");

		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("deleteSchedulesHome.jsp").forward(request, response);

		}
	}



}
