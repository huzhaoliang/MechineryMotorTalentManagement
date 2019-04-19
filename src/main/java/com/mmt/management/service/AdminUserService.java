package com.mmt.management.service;

import com.mmt.management.entity.AdminUser;

public interface AdminUserService {
	AdminUser checkUserByNameAndPwd(String name, String pwd);
	
	AdminUser checkUserByName(String name);
}
