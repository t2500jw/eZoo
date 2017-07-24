package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.UserDAO;

/**
 * Servlet implementation class CreateNewUSer
 */
@WebServlet("/CreateNewUser")
public class CreateNewUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters		
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");		
		
		//Call DAO method		
		UserDAO dao = DAOUtilities.getUserDao();
		try { 
			String responseMessage = dao.createUserAccount(username, password1, password2);
			System.out.println(responseMessage);
			request.getSession().setAttribute("message", "User successfully created!");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("login.jsp");
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the new user!");
			request.getSession().setAttribute("messageClass", "alert-danger");			
			request.getRequestDispatcher("register.jsp").forward(request, response);

		}
	}

}
