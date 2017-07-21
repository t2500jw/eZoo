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
 * Servlet implementation class UpdateFeedingScheduleServlet
 */
@WebServlet({ "/updateFeedingSchedule" })
public class UpdateFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFeedingScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		// Grab a list of Animals from the Database
		FeedingScheduleDAO feedingDao = DAOUtilities.getFeedingScheduleDao();
		FeedingSchedule schedule = feedingDao.GetFeedingSchedule(id);
		
		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("schedule", schedule);					
		
		request.getRequestDispatcher("updateFeedingSchedule.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FeedingSchedule schedule = new FeedingSchedule();
		schedule.setFood(request.getParameter("food"));
		schedule.setNotes(request.getParameter("notes"));
		schedule.setRecurrence(Integer.parseInt(request.getParameter("recurrence")));
		schedule.setSchedule_ID(Long.parseLong(request.getParameter("id")));
		schedule.setTime(request.getParameter("time"));
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.UpdateFeedingSchedule(schedule);
		}catch(Exception e) {
			
		}
		
		request.getRequestDispatcher("animalCareHome.jsp").forward(request, response);
	}

}
