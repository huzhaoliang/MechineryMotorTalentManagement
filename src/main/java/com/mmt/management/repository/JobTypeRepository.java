package com.mmt.management.repository;

import com.mmt.management.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mmt.management.entity.JobType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobTypeRepository extends JpaSpecificationExecutor<JobType>, JpaRepository<JobType, Long>{

}
