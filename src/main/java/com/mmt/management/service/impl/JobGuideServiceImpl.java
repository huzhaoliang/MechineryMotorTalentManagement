package com.mmt.management.service.impl;

import java.util.List;

import com.mmt.management.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.mmt.management.entity.JobGuide;
import com.mmt.management.repository.JobGuideRepository;
import com.mmt.management.service.JobGuideService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;

@Service("JobGuideService")
public class JobGuideServiceImpl implements JobGuideService{
	@Autowired
	private JobGuideRepository jobGuideRepository;

	@Override
	public JobGuide saveJobGuide(JobGuide jobGuide) {
		return jobGuideRepository.saveAndFlush(jobGuide);
	}

	@Override
	public Page<JobGuide> getJobGuides(String title, int pageNumber, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "publishTime");
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
		Page<JobGuide> jobGuides = null;
		if("".equals(title)){
			jobGuides = jobGuideRepository.findAll(request);
		}else{
			Specification<JobGuide> spec = new Specification<JobGuide>() {
				public Predicate toPredicate(Root<JobGuide> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Path<String> codeAttribute = root.get("title");
					Predicate p1 = cb.like(codeAttribute, "%"+title+"%");
					Predicate p = cb.and(p1);
					return p;
				}
			};
			jobGuides = jobGuideRepository.findAll(spec, request);
		}
		return jobGuides;
	}

	@Override
	public JobGuide getOneJobGuide(Long id) {
		return jobGuideRepository.getOne(id);
	}

	@Override
	public void deleteJobGuideById(Long id) {
		jobGuideRepository.deleteById(id);
	}

}
