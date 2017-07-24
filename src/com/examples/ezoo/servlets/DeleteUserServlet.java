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
import com.examples.ezoo.dao.UserDAO;
import com.examples.ezoo.model.FeedingSchedule;
import com.examples.ezoo.model.Users;


/**
 * Servlet implementation class DeleteFeedingScheduleServlet
 */
@WebServlet(description = "This servlet is the main interface into the deleting users", urlPatterns = { "/deleteUser" })
public class DeleteUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Grab users from database
		UserDAO dao = DAOUtilities.getUserDao();
		List<Users> users = dao.getUsers();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("users", users);				
		
		request.getRequestDispatcher("/admin/deleteUser.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		
		String username = request.getParameter("username");
		
		//Call DAO method		
		UserDAO dao = DAOUtilities.getUserDao();
		try {
			dao.deleteUserAccount(username);
			request.getSession().setAttribute("message", "Deleted user successfully!");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("deleteUser");

		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("/admin/deleteUser.jsp").forward(request, response);

		}
	}



}
