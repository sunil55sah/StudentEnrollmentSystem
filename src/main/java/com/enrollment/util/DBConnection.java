package com.enrollment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println(" MySQL JDBC Driver loaded successfully.");
			} catch (ClassNotFoundException e) {
				System.out.println("❌ Failed to load JDBC Driver.");
				e.printStackTrace();
			}

			// Read Railway environment variables
			String host = System.getenv("MYSQLHOST");
			String port = System.getenv("MYSQLPORT");
			String db = System.getenv("MYSQLDATABASE");
			String user = System.getenv("MYSQLUSER");
			String pass = System.getenv("MYSQLPASSWORD");

			String jdbcURL = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false";
			System.out.println("🔌 Connecting to: " + jdbcURL);

			// Establish connection
			connection = DriverManager.getConnection(jdbcURL, user, pass);
			System.out.println(" Connected to Railway MySQL DB.");
		}
		return connection;
	}
}
