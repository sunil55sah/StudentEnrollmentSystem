package com.enrollment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.enrollment.dao.CourseDAO;
import com.enrollment.model.Course;
import com.enrollment.util.DBConnection;

public class CourseDAOImpl implements CourseDAO {

	private static final String INSERT_SQL = "INSERT INTO course (course_name, description, instructor) VALUES (?, ?, ?)";
	private static final String GET_ALL_SQL = "SELECT * FROM course";
	private static final String GET_BY_ID_SQL = "SELECT * FROM course WHERE id = ?";
	private static final String UPDATE_SQL = "UPDATE course SET course_name = ?, description = ?, instructor = ? WHERE id = ?";
	private static final String DELETE_SQL = "DELETE FROM course WHERE id = ?";

	@Override
	public boolean addCourse(Course course) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {

			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getDescription());
			stmt.setString(3, course.getInstructor());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(GET_ALL_SQL)) {

			while (rs.next()) {
				Course course = new Course(rs.getInt("id"), rs.getString("course_name"), rs.getString("description"),
						rs.getString("instructor"));
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	public Course getCourseById(int id) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_ID_SQL)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Course(rs.getInt("id"), rs.getString("course_name"), rs.getString("description"),
						rs.getString("instructor"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateCourse(Course course) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {

			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getDescription());
			stmt.setString(3, course.getInstructor());
			stmt.setInt(4, course.getId());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCourse(int id) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {

			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
