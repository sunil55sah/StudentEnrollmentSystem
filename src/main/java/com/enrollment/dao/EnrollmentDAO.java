package com.enrollment.dao;

import java.util.List;

import com.enrollment.model.Enrollment;

public interface EnrollmentDAO {
	boolean enrollStudent(Enrollment enrollment);

	boolean deleteEnrollment(int studentId, int courseId);

	List<Enrollment> getEnrollmentsByStudentId(int studentId);
}
