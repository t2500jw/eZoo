package com.examples.ezoo.servlets;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.UserDAO;

/**
 * Servlet implementation class UpdateFeedingScheduleServlet
 */
@WebServlet({ "/changePassword" })
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		Principal p = request.getUserPrincipal();
		String username = p.getName();
		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("request", request);	
		request.getSession().setAttribute("username", username);
				
		request.getRequestDispatcher("changePassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");		
		
		UserDAO dao = DAOUtilities.getUserDao();
		try {
				String retValue = dao.updateUserPassword(username, password1, password2);
				request.getSession().setAttribute("message", retValue);
				request.getSession().setAttribute("messageClass", "alert-success");
				response.sendRedirect("animalCare");
		}catch(Exception e) {
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem changing your password!");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("changePassword.jsp").forward(request, response);
		}	
		
	}

}
