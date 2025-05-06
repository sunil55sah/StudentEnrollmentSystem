package com.enrollment.servlet;

import java.io.IOException;

import com.enrollment.dao.AdminDAO;
import com.enrollment.dao.StudentDAO;
import com.enrollment.dao.impl.AdminDAOImpl;
import com.enrollment.dao.impl.StudentDAOImpl;
import com.enrollment.model.Admin;
import com.enrollment.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		AdminDAO adminDAO = new AdminDAOImpl();
		StudentDAO studentDAO = new StudentDAOImpl();

		// Try admin login first
		Admin admin = adminDAO.getAdminByEmailAndPassword(email, password);
		if (admin != null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			response.sendRedirect("dashboard");
			return;
		}

		// Try student login if not admin
		Student student = studentDAO.login(email, password);
		if (student != null) {
			HttpSession session = request.getSession();
			session.setAttribute("student", student);
			response.sendRedirect("dashboard"); // dashboard.jsp mapped through DashboardServlet
		} else {
			response.sendRedirect("login.jsp?error=invalid"); // Show login error
		}
	}
}
