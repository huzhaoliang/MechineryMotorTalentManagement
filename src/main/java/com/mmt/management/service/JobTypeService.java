package com.mmt.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mmt.management.entity.JobType;

public interface JobTypeService {
	JobType saveJobType(JobType jobType);

	Page<JobType> getJobTypes(String type, int pageNumber, int pageSize);

	void deleteJobTypeById(Long id);

	JobType getJobTypeById(Long id);

	List<JobType> getParentJobTypes();

	List<JobType> getJobTypeByParent(Long id);
	
	List<JobType> findAllLevelOneJobType();
	
	List<JobType> findAllLevelTwoJobType();
	
	List<JobType> findAllLevelThreeJobType();
}
