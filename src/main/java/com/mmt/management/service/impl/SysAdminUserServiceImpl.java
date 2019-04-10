package com.mmt.management.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mmt.management.entity.SysAdminUser;
import com.mmt.management.entity.SysPermission;
import com.mmt.management.entity.SysRole;
import com.mmt.management.repository.SysAdminUserRepository;
import com.mmt.management.service.SysAdminUserService;

@Service(value="SysAdminUserService")
public class SysAdminUserServiceImpl implements SysAdminUserService{
	
	@Autowired
	private SysAdminUserRepository adminRepository;

	@Override
	public SysAdminUser checkUserByNameAndPwd(String name, String password) {
		return adminRepository.checkUserByNameAndPwd(name, password);
	}

	@Cacheable(cacheNames = "authority", key = "#name")
	@Override
	public SysAdminUser checkUserByName(String name) {
		SysRole role = new SysRole("ADMIN", "管理员");
		SysPermission p1 = new SysPermission();
		p1.setCode("MAIN");
		p1.setName("主界面");
		p1.setUrl("/manage/url");
		role.setPermissionList(Arrays.asList(p1));
		SysAdminUser user = adminRepository.checkUserByName(name);
		if(null == user) return null;
		else {
			user.setRoleList(Arrays.asList(role));
			return user;
		}
	}
}
