package com.mmt.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.mmt.management.entity.JobGuide;
import com.mmt.management.repository.JobGuideRepository;
import com.mmt.management.service.JobGuideService;

public class JobGuideServiceImpl implements JobGuideService{
	@Autowired
	private JobGuideRepository jobGuideRepository;

	@Override
	public JobGuide saveJobGuide(JobGuide jobGuide) {
		return jobGuideRepository.saveAndFlush(jobGuide);
	}

	@Override
	public Page<JobGuide> getJobGuides(int pageNumber, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "publishTime");
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
		Page<JobGuide> jobGuides = jobGuideRepository.findAll(request);
		return jobGuides;
	}

	@Override
	public void deleteJobGuides(List<JobGuide> jobGuides) {
		jobGuideRepository.deleteInBatch(jobGuides);
	}

	@Override
	public JobGuide getOneJobGuide(Long id) {
		return jobGuideRepository.getOne(id);
	}

	@Override
	public void deleteJobGuideById(Long id) {
		jobGuideRepository.deleteJobGuideById(id);
	}

}
