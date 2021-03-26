package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.UserDAO;
import edu.poly.model.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet({ "/Admin/UserManagement", "/UserManagement", "/Admin/UserManagement/create",
		"/Admin/UserManagement/update", "/Admin/UserManagement/delete", "/Admin/UserManagement/reset",
		"/Admin/UserManagement/edit"

})
public class UserManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("edit")) {
			edit(request, response);
			return;
		} else if (url.contains("delete")) {
			delete(request, response);
			return;
		} else if (url.contains("reset")) {
			reset(request, response);
			return;
		}

		User user = new User();
		findAll(request, response);
		request.setAttribute("user", user);
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			UserDAO dao = new UserDAO();

			List<User> list = dao.findAll();

			request.setAttribute("users", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

// EDIT USER
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");

		if (username == null) {
			request.setAttribute("error", "username is required");
			PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
			return;
		}

		try {
			UserDAO dao = new UserDAO();
			User user = dao.findById(username);

			request.setAttribute("user", user);
			findAll(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

// FIND ALL

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("create")) {
			create(request, response);
			return;
		} else if (url.contains("update")) {
			update(request, response);
			return;
		} else if (url.contains("delete")) {
			delete(request, response);
			return;
		} else if (url.contains("reset")) {
			reset(request, response);
			return;
		}

	}
// RESET
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}
// DELETE
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		try {
			UserDAO dao = new UserDAO();
			User user = dao.findById(username);
			
			if (username == null) {
				request.setAttribute("error", "uesrname is required");
				PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
			}
			dao.delete(username);
			request.setAttribute("message", "user is deleted!");
			request.setAttribute("user", new User());
			
			findAll(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Error" + e.getMessage());
		}
		
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
		
	}

//  UPDATE VIDEO
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			UserDAO dao = new UserDAO();

			dao.update(user);
			
			request.setAttribute("user", user);
			request.setAttribute("message", "update sucessfully!!");

			findAll(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

//CREATE USER
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			UserDAO dao = new UserDAO();

			dao.insert(user);
			request.setAttribute("user", user);
			request.setAttribute("message", "User is inserted");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

	}

}
