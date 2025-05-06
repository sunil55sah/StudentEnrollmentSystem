package com.enrollment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.enrollment.dao.StudentDAO;
import com.enrollment.model.Student;
import com.enrollment.util.DBConnection;

public class StudentDAOImpl implements StudentDAO {

	private static final String INSERT_SQL = "INSERT INTO students (username, password, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_SQL = "SELECT * FROM students";
	private static final String GET_BY_ID_SQL = "SELECT * FROM students WHERE id = ?";
	private static final String UPDATE_SQL = "UPDATE students SET username = ?, password = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?";
	private static final String DELETE_SQL = "DELETE FROM students WHERE id = ?";
	private static final String LOGIN_SQL = "SELECT * FROM students WHERE email = ? AND password = ?";

	@Override
	public boolean addStudent(Student student) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {

			stmt.setString(1, student.getUsername());
			stmt.setString(2, student.getPassword());
			stmt.setString(3, student.getFirstName());
			stmt.setString(4, student.getLastName());
			stmt.setString(5, student.getEmail());

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();

		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(GET_ALL_SQL)) {

			while (rs.next()) {
				students.add(new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("first_name"), rs.getString("last_name"), rs.getString("email")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	@Override
	public Student getStudentById(int id) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_ID_SQL)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateStudent(Student student) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {

			stmt.setString(1, student.getUsername());
			stmt.setString(2, student.getPassword());
			stmt.setString(3, student.getFirstName());
			stmt.setString(4, student.getLastName());
			stmt.setString(5, student.getEmail());
			stmt.setInt(6, student.getId());

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteStudent(int id) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {

			stmt.setInt(1, id);
			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Student login(String email, String password) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(LOGIN_SQL)) {

			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean registerStudent(Student student) {
		return addStudent(student); // Reusing addStudent method
	}
}
