package com.mmt.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mmt.management.entity.EnterpriseUser;

public interface EnterpriseService {
	EnterpriseUser insertEnterprise(EnterpriseUser user);
	
	Page<EnterpriseUser> getEnterpriseUsers(int pageNumber, int pageSize);
	
	List<EnterpriseUser> getAllEnterprise();
}
