package com.enrollment.dao;

import com.enrollment.model.Admin;

public interface AdminDAO {
	Admin getAdminByEmailAndPassword(String email, String password);
}
