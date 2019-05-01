package com.mmt.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.mmt.management.entity.JobType;
import com.mmt.management.repository.JobTypeRepository;
import com.mmt.management.service.JobTypeService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service("JobTypeService")
public class JobTypeServiceImpl implements JobTypeService{

	@Autowired
	private JobTypeRepository jobTypeRepository;

	@Override
	public JobType saveJobType(JobType jobType) {
		return jobTypeRepository.saveAndFlush(jobType);
	}

	@Override
	public Page<JobType> getJobTypes(String type, int pageNumber, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
		Page<JobType> types = null;
		if("".equals(type)){
			types = jobTypeRepository.findAll(request);
		}else{
			Specification<JobType> spec = new Specification<JobType>() {
				public Predicate toPredicate(Root<JobType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate p = null;
					Path<String> typeAttribute = root.get("type");
					Predicate p1 = cb.like(typeAttribute, "%"+type+"%");
					p = cb.and(p1);
					return p;
				}
			};
			types = jobTypeRepository.findAll(spec, request);
		}
		return types;
	}

	@Override
	public void deleteJobTypeById(Long id) {
		jobTypeRepository.deleteById(id);
	}

	@Override
	public JobType getJobTypeById(Long id) {
		return jobTypeRepository.getOne(id);
	}

	@Override
	public List<JobType> getParentJobTypes() {
		return jobTypeRepository.getParentJobTypes();
	}

	@Override
	public List<JobType> getJobTypeByParent(Long parentId) {
		return jobTypeRepository.getJobTypeByParent(parentId);
	}

}
