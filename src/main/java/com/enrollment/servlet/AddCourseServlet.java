package com.enrollment.servlet;

import java.io.IOException;

import com.enrollment.dao.CourseDAO;
import com.enrollment.dao.impl.CourseDAOImpl;
import com.enrollment.model.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addcourse")
public class AddCourseServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get form values
		String courseName = request.getParameter("courseName");
		String instructor = request.getParameter("instructor");
		String description = request.getParameter("description");

		// Set values to Course object

		Course course = new Course();
		course.setCourseName(courseName);
		course.setInstructor(instructor);
		course.setDescription(description);

		// Insert course using DAO
		CourseDAO courseDAO = new CourseDAOImpl();
		boolean added = courseDAO.addCourse(course);

		if (added) {
			response.sendRedirect("addcourse.jsp?success=true");
		} else {
			response.sendRedirect("addcourse.jsp?success=false");
		}
	}
}
