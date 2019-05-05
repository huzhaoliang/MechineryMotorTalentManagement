package com.mmt.management.service.impl;

import java.util.Arrays;

import com.mmt.management.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mmt.management.repository.AdminUserRepository;
import com.mmt.management.service.AdminUserService;

import javax.persistence.criteria.*;

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

	@Override
	public AdminUser saveAdminUser(AdminUser adminUser) {
		return adminRepository.saveAndFlush(adminUser);
	}

	@Override
	public Page<AdminUser> getAdminUsersByQueries(String name, int pageNumber, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
		Page<AdminUser> adminUsers = null;
		if("".equals(name)){
			adminUsers = adminRepository.findAll(request);
		}else{
			Specification<AdminUser> spec = new Specification<AdminUser>() {
				public Predicate toPredicate(Root<AdminUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Path<String> nameAttribute = root.get("name");
					Predicate p1 = cb.like(nameAttribute, "%"+name+"%");
					Predicate p = cb.and(p1);
					return p;
				}
			};
			adminUsers = adminRepository.findAll(spec, request);
		}
		return adminUsers;
	}

	@Override
	public void deleteAdminUserById(Long id) {
		adminRepository.deleteById(id);
	}

}
