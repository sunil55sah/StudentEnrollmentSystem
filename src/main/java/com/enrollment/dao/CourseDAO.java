package com.enrollment.dao;

import java.util.List;

import com.enrollment.model.Course;

public interface CourseDAO {
	boolean addCourse(Course course);

	List<Course> getAllCourses();

	Course getCourseById(int id);

	boolean updateCourse(Course course); // ✅ Make sure this exists

	boolean deleteCourse(int id); // ✅ Make sure this exists
}
