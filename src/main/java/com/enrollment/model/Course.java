package com.enrollment.model;

public class Course {
	private int id;
	private String courseName;
	private String description;
	private String instructor;

	// Constructors
	public Course() {
	}

	public Course(int id, String courseName, String description, String instructor) {
		this.id = id;
		this.courseName = courseName;
		this.description = description;
		this.instructor = instructor;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
}
