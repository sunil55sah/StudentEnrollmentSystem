package com.enrollment.servlet;

import java.io.IOException;

import com.enrollment.dao.StudentDAO;
import com.enrollment.dao.impl.StudentDAOImpl;
import com.enrollment.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Fetch form data
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Split the name into first and last name (basic approach)
		String[] nameParts = name.split(" ");
		String firstName = nameParts[0];
		String lastName = (nameParts.length > 1) ? nameParts[1] : ""; // Handle case where no last name is provided

		// Create Student object using the full constructor
		Student student = new Student(0, name, password, firstName, lastName, email); // id is set to 0 for new
																						// registration
		StudentDAO studentDAO = new StudentDAOImpl();

		// Attempt to register the student
		boolean isRegistered = studentDAO.registerStudent(student);

		if (isRegistered) {
			// Redirect to login page with success message
			response.sendRedirect("login.jsp?message=registered");
		} else {
			// Redirect back to registration page with error message
			response.sendRedirect("register.jsp?error=exists");
		}
	}
}
