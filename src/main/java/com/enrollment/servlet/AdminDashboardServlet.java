package com.enrollment.servlet;

import java.io.IOException;
import java.util.List;

import com.enrollment.dao.CourseDAO;
import com.enrollment.dao.impl.CourseDAOImpl;
import com.enrollment.model.Admin;
import com.enrollment.model.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Admin admin = (Admin) session.getAttribute("admin");

		if (admin == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		CourseDAO courseDAO = new CourseDAOImpl();
		List<Course> courses = courseDAO.getAllCourses();

		request.setAttribute("courses", courses);
		request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
	}
}
