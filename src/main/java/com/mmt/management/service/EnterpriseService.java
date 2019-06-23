package com.mmt.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mmt.management.entity.EnterpriseUser;

public interface EnterpriseService {
	
	Page<EnterpriseUser> getEnterprises(String name, String status, int pageNumber, int pageSize);
	
	List<EnterpriseUser> getAllEnterprise();

	void updateEnterpriseStatus(Long status, Long id);

	EnterpriseUser getOne(Long id);
}
