package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
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
 * Servlet implementation class AnimalCareServlet
 */
@WebServlet(description = "This servlet is the main interface into the Feeding Schedule", urlPatterns = { "/feedingSchedules" })
public class FeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Animals from the Database
		FeedingScheduleDAO feedingDao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> schedules = feedingDao.GetFeedingSchedules();
		AnimalDAO animalsDao = DAOUtilities.getAnimalDao();
		List<Animal> animals = animalsDao.getAllAnimals();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("schedules", schedules);	
		request.getSession().setAttribute("animals",animals);
		
		
		request.getRequestDispatcher("feedingSchedulesHome.jsp").forward(request, response);
	}	
	


}
