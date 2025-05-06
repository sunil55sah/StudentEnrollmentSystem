package com.enrollment.servlet;

import java.io.IOException;

import com.enrollment.dao.EnrollmentDAO;
import com.enrollment.dao.impl.EnrollmentDAOImpl;
import com.enrollment.model.Enrollment;
import com.enrollment.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get course ID from request parameter
		String courseIdParam = request.getParameter("courseId");
		HttpSession session = request.getSession(false); // don't create if doesn't exist

		// Validate session and student login
		if (session == null || session.getAttribute("student") == null || courseIdParam == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		try {
			int courseId = Integer.parseInt(courseIdParam);
			Student student = (Student) session.getAttribute("student");

			Enrollment enrollment = new Enrollment(student.getId(), courseId);
			EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();

			boolean success = enrollmentDAO.enrollStudent(enrollment);

			if (success) {
				response.sendRedirect("dashboard");
			} else {
				response.sendRedirect("dashboard?error=enrollment_failed");
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("dashboard?error=invalid_course_id");
		}
	}
}
