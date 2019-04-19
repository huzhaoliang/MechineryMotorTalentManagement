package com.mmt.management.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mmt.management.entity.AdminUser;
import com.mmt.management.entity.Menu;
import com.mmt.management.entity.Role;
import com.mmt.management.repository.AdminUserRepository;
import com.mmt.management.service.AdminUserService;

@Service(value="AdminUserService")
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	private AdminUserRepository adminRepository;

	@Override
	public AdminUser checkUserByNameAndPwd(String name, String password) {
		return adminRepository.checkUserByNameAndPwd(name, password);
	}

	@Cacheable(cacheNames = "authority", key = "#name")
	@Override
	public AdminUser checkUserByName(String name) {
		Role role = new Role("ADMIN", "管理员");
		Menu p1 = new Menu();
		p1.setCode("MAIN");
		p1.setName("主界面");
		p1.setUrl("/manage/url");
		role.setPermissionList(Arrays.asList(p1));
		AdminUser user = adminRepository.checkUserByName(name);
		if(null == user) return null;
		else {
			user.setRoleList(Arrays.asList(role));
			return user;
		}
	}
}
