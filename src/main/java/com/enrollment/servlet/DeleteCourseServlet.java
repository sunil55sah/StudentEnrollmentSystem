package com.enrollment.servlet;

import java.io.IOException;

import com.enrollment.dao.CourseDAO;
import com.enrollment.dao.impl.CourseDAOImpl;
import com.enrollment.model.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteCourse")
public class DeleteCourseServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Admin admin = (Admin) session.getAttribute("admin");

		// Optional: allow both Admin and Student (with condition in dashboard)
		if (admin == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String courseIdParam = request.getParameter("courseId");

		if (courseIdParam != null) {
			try {
				int courseId = Integer.parseInt(courseIdParam);
				CourseDAO courseDAO = new CourseDAOImpl();
				courseDAO.deleteCourse(courseId);

				session.setAttribute("msg", "Course deleted successfully.");
			} catch (NumberFormatException e) {
				session.setAttribute("msg", "Invalid Course ID.");
			} catch (Exception e) {
				session.setAttribute("msg", "Error deleting course.");
			}
		} else {
			session.setAttribute("msg", "Course ID not provided.");
		}

		response.sendRedirect("dashboard");
	}
}
