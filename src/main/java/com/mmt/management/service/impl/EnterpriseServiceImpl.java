package com.mmt.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mmt.management.entity.EnterpriseUser;
import com.mmt.management.repository.EnterpriseRepository;
import com.mmt.management.service.EnterpriseService;

import javax.persistence.criteria.*;

@Service("EnterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService{

	@Autowired
	private EnterpriseRepository enterpriseRepository;

	@Override
	public Page<EnterpriseUser> getEnterprises(String name, String status, int pageNumber, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
		Page<EnterpriseUser> enterprises = null;
		if("".equals(status) && "".equals(name)){
			enterprises = enterpriseRepository.findAll(request);
		}else{
			Specification<EnterpriseUser> spec = new Specification<EnterpriseUser>() {
				public Predicate toPredicate(Root<EnterpriseUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate p = null;
					if(!"".equals(name)){
						Path<String> nameAttribute = root.get("name");
						Predicate p1 = cb.like(nameAttribute, "%"+name+"%");
						p = cb.and(p1);
						if(!"-1".equals(status)) {
							Path<Long> statusAttribute = root.get("status");
							Predicate p2 = cb.equal(statusAttribute, status);
							p = cb.and(p1, p2);
						}
					}else{
						if(!"-1".equals(status)) {
							Path<Long> parentIdAttribute = root.get("status");
							Predicate p2 = cb.equal(parentIdAttribute, status);
							p = cb.and(p2);
						}
					}
					return p;
				}
			};
			enterprises = enterpriseRepository.findAll(spec, request);
		}
		return enterprises;
	}

	@Override
	public List<EnterpriseUser> getAllEnterprise() {
		return enterpriseRepository.findAll();
	}

	@Override
	public void updateEnterpriseStatus(Long status, Long id) {
		enterpriseRepository.updateEnterpriseStatus(status, id);
	}

	@Override
	public EnterpriseUser getOne(Long id) {
		return enterpriseRepository.getOne(id);
	}

}
