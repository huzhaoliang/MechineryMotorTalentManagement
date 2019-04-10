package com.mmt.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mmt.management.entity.JobGuide;

public interface JobGuideService {
	JobGuide saveJobGuide(JobGuide jobGuide);
	
	Page<JobGuide> getJobGuides(int pageNumber, int pageSize);
	
	void deleteJobGuides(List<JobGuide> jobFairs);
	
	JobGuide getOneJobGuide(Long id);
	
	void deleteJobGuideById(Long id);
}
