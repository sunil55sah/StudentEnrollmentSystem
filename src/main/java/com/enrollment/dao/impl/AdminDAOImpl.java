package com.enrollment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.enrollment.dao.AdminDAO;
import com.enrollment.model.Admin;
import com.enrollment.util.DBConnection;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public Admin getAdminByEmailAndPassword(String email, String password) {
		Admin admin = null;
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin = new Admin(rs.getInt("id"), rs.getString("email"), rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
}
