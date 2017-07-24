package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.UserRoleDAO;
import com.examples.ezoo.model.User_Role;

/**
 * Servlet implementation class ChangeUserRoleServlet
 */
@WebServlet("/changeUserRole")
public class ChangeUserRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserRoleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Grab users from database
				UserRoleDAO dao = DAOUtilities.getUserRoleDao();
				List<User_Role> users = dao.GetUserRoles();

				// Populate the list into a variable that will be stored in the session
				request.getSession().setAttribute("users", users);				
				
				request.getRequestDispatcher("/admin/changeUserRole.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String[]> tableData = request.getParameterMap();
		String[] idsToUpdate = tableData.get("selectedIds");
		for (String id : idsToUpdate){
		    String kwdParamName = "kwd"+id;
		    String kwd = tableData.get(kwdParamName)[0];
		}
		
		
		String[] users = request.getParameterValues("userRoles");
		String username = request.getParameter("name");	
		String role = request.getParameter("role");
		
		
		//Call DAO method		
		UserRoleDAO dao = DAOUtilities.getUserRoleDao();
		try {
			boolean result = dao.SetUserRole(username, role);
			//request.getSession().setAttribute("message", "Feeding Schedule successfully assigned");
			request.getSession().setAttribute("message", result + " rows affected");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("changeUserRole");			


		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem assigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);

		}
	}

}
