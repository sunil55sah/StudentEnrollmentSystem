package com.enrollment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// Read Railway environment variables
			String jdbcURL = System.getenv("MYSQL_URL");
			String dbUser = System.getenv("MYSQLUSER");
			String dbPassword = System.getenv("MYSQLPASSWORD");

			// Establish connection
			connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		}
		return connection;
	}
}
