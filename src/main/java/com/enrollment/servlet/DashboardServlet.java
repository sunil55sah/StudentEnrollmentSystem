package com.enrollment.servlet;

import java.io.IOException;
import java.util.List;

import com.enrollment.dao.CourseDAO;
import com.enrollment.dao.EnrollmentDAO;
import com.enrollment.dao.impl.CourseDAOImpl;
import com.enrollment.dao.impl.EnrollmentDAOImpl;
import com.enrollment.model.Admin;
import com.enrollment.model.Course;
import com.enrollment.model.Enrollment;
import com.enrollment.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		CourseDAO courseDAO = new CourseDAOImpl();

		// Check if student is logged in
		Student student = (Student) session.getAttribute("student");
		if (student != null) {
			EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();
			List<Course> courses = courseDAO.getAllCourses();
			List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudentId(student.getId());

			request.setAttribute("student", student);
			request.setAttribute("courses", courses);
			request.setAttribute("enrollments", enrollments);

			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			return;
		}

		// Check if admin is logged in
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin != null) {
			List<Course> courses = courseDAO.getAllCourses();
			request.setAttribute("courses", courses);
			request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
			return;
		}

		// If neither admin nor student is logged in
		response.sendRedirect("login.jsp");
	}
}
