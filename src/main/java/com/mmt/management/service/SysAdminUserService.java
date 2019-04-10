package com.mmt.management.service;

import com.mmt.management.entity.SysAdminUser;

public interface SysAdminUserService {
	SysAdminUser checkUserByNameAndPwd(String name, String pwd);
	
	SysAdminUser checkUserByName(String name);
}
