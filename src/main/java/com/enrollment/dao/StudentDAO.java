package com.enrollment.dao;

import java.util.List;

import com.enrollment.model.Student;

public interface StudentDAO {

	boolean addStudent(Student student);

	List<Student> getAllStudents();

	Student getStudentById(int id);

	boolean updateStudent(Student student);

	boolean deleteStudent(int id);

	Student login(String email, String password);

	// Add the registerStudent method
	boolean registerStudent(Student student); // Method for student registration
}
