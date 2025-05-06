package com.enrollment.servlet;

import java.io.IOException;
import java.util.List;

import com.enrollment.dao.StudentDAO;
import com.enrollment.dao.impl.StudentDAOImpl;
import com.enrollment.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	private StudentDAO studentDAO = new StudentDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("edit".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Student student = studentDAO.getStudentById(id);
			request.setAttribute("student", student);
		}

		List<Student> students = studentDAO.getAllStudents();
		request.setAttribute("students", students);
		request.getRequestDispatcher("student.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			Student student = new Student();
			student.setUsername(request.getParameter("username"));
			student.setPassword(request.getParameter("password"));
			student.setFirstName(request.getParameter("first_name"));
			student.setLastName(request.getParameter("last_name"));
			student.setEmail(request.getParameter("email"));
			studentDAO.addStudent(student);

		} else if ("update".equals(action)) {
			Student student = new Student();
			student.setId(Integer.parseInt(request.getParameter("id")));
			student.setUsername(request.getParameter("username"));
			student.setPassword(request.getParameter("password"));
			student.setFirstName(request.getParameter("first_name"));
			student.setLastName(request.getParameter("last_name"));
			student.setEmail(request.getParameter("email"));
			studentDAO.updateStudent(student);

		} else if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			studentDAO.deleteStudent(id);
		}

		response.sendRedirect("student");
	}
}
