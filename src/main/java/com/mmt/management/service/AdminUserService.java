package com.mmt.management.service;

import com.mmt.management.entity.AdminUser;
import org.springframework.data.domain.Page;

public interface AdminUserService {
	AdminUser checkUserByNameAndPwd(String name, String pwd);
	
	AdminUser checkUserByName(String name);

	AdminUser saveAdminUser(AdminUser adminUser);

	Page<AdminUser> getAdminUsersByQueries(String name, int pageNumber, int pageSize);

	void deleteAdminUserById(Long id);
}
