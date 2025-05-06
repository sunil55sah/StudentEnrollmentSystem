package com.enrollment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_course_db";
	private static final String JDBC_USER = "root"; // Replace with your DB username
	private static final String JDBC_PASSWORD = "root"; // Replace with your DB password

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
}
