package com.enrollment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enrollment.dao.EnrollmentDAO;
import com.enrollment.model.Enrollment;
import com.enrollment.util.DBConnection;

public class EnrollmentDAOImpl implements EnrollmentDAO {

	private static final String ENROLL_SQL = "INSERT INTO enrollment (student_id, course_id) VALUES (?, ?)";
	private static final String CHECK_EXISTING_SQL = "SELECT COUNT(*) FROM enrollment WHERE student_id = ? AND course_id = ?";
	private static final String GET_BY_STUDENT_ID_SQL = "SELECT e.id, e.student_id, e.course_id, e.enrollment_date, c.course_name "
			+ "FROM enrollment e JOIN course c ON e.course_id = c.id WHERE e.student_id = ?";
	private static final String DELETE_ENROLLMENT_SQL = "DELETE FROM enrollment WHERE student_id = ? AND course_id = ?";

	@Override
	public boolean enrollStudent(Enrollment enrollment) {
		try (Connection conn = DBConnection.getConnection()) {

			// Check if already enrolled
			try (PreparedStatement checkStmt = conn.prepareStatement(CHECK_EXISTING_SQL)) {
				checkStmt.setInt(1, enrollment.getStudentId());
				checkStmt.setInt(2, enrollment.getCourseId());
				ResultSet rs = checkStmt.executeQuery();

				if (rs.next() && rs.getInt(1) > 0) {
					System.out.println("Enrollment already exists. Skipping insertion.");
					return false;
				}
			}

			// Insert new enrollment
			try (PreparedStatement insertStmt = conn.prepareStatement(ENROLL_SQL)) {
				insertStmt.setInt(1, enrollment.getStudentId());
				insertStmt.setInt(2, enrollment.getCourseId());
				int rows = insertStmt.executeUpdate();
				return rows > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
		List<Enrollment> enrollments = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_BY_STUDENT_ID_SQL)) {

			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Enrollment enrollment = new Enrollment();
				enrollment.setId(rs.getInt("id"));
				enrollment.setStudentId(rs.getInt("student_id"));
				enrollment.setCourseId(rs.getInt("course_id"));
				enrollment.setEnrollmentDate(rs.getTimestamp("enrollment_date"));
				enrollment.setCourseName(rs.getString("course_name"));
				enrollments.add(enrollment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enrollments;
	}

	@Override
	public boolean deleteEnrollment(int studentId, int courseId) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_ENROLLMENT_SQL)) {

			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			int rows = stmt.executeUpdate();
			return rows > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
