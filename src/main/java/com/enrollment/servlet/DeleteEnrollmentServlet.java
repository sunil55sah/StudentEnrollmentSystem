package com.enrollment.servlet;

import java.io.IOException;

import com.enrollment.dao.EnrollmentDAO;
import com.enrollment.dao.impl.EnrollmentDAOImpl;
import com.enrollment.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteEnrollment")
public class DeleteEnrollmentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("student") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		Student student = (Student) session.getAttribute("student");
		String courseIdParam = request.getParameter("courseId");

		if (courseIdParam == null) {
			response.sendRedirect("dashboard?error=missing_course_id");
			return;
		}

		try {
			int courseId = Integer.parseInt(courseIdParam);
			EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();
			boolean deleted = enrollmentDAO.deleteEnrollment(student.getId(), courseId);

			if (deleted) {
				response.sendRedirect("dashboard");
			} else {
				response.sendRedirect("dashboard?error=enrollment_delete_failed");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("dashboard?error=invalid_course_id");
		}
	}
}
