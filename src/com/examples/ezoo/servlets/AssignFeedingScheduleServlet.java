package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AssignFeedingScheduleServlet
 */
@WebServlet("/assignFeedingSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Grab a list of Animals from the Database
				AnimalDAO animalDao = DAOUtilities.getAnimalDao();
				List<Animal> animals = animalDao.getAllAnimals();

				// Populate the list into a variable that will be stored in the session
				request.getSession().setAttribute("animals", animals);
		// Grab a list of all Feeding Schedules from the Database
				FeedingScheduleDAO feedingScheduleDao = DAOUtilities.getFeedingScheduleDao();
				List<FeedingSchedule> schedules = feedingScheduleDao.GetFeedingSchedules();
				request.getSession().setAttribute("schedules", schedules);				
				request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		//We MUST convert to a Long since parameters are always Strings
		
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");	
		
		
		//Call DAO method		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			int result = dao.AssignFeedingSchedule(id, name);
			//request.getSession().setAttribute("message", "Feeding Schedule successfully assigned");
			request.getSession().setAttribute("message", result + " rows affected");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("assignFeedingSchedule");			


		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem assigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);

		}
	}

}
