package com.enrollment.servlet;

import java.io.IOException;
import java.util.List;

import com.enrollment.dao.CourseDAO;
import com.enrollment.dao.impl.CourseDAOImpl;
import com.enrollment.model.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {

	private CourseDAO courseDAO = new CourseDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("edit".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Course course = courseDAO.getCourseById(id);
			request.setAttribute("course", course);
		}

		List<Course> courses = courseDAO.getAllCourses();
		request.setAttribute("courses", courses);
		request.getRequestDispatcher("course.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("add".equalsIgnoreCase(action)) {
			Course course = new Course();
			course.setCourseName(request.getParameter("name"));
			course.setInstructor(request.getParameter("instructor"));
			course.setDescription(request.getParameter("description"));

			courseDAO.addCourse(course);

		} else if ("update".equalsIgnoreCase(action)) {
			Course course = new Course();
			course.setId(Integer.parseInt(request.getParameter("id")));
			course.setCourseName(request.getParameter("name"));
			course.setInstructor(request.getParameter("instructor"));
			course.setDescription(request.getParameter("description"));

			courseDAO.updateCourse(course);

		} else if ("delete".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			courseDAO.deleteCourse(id);
		}

		response.sendRedirect("course");
	}
}
