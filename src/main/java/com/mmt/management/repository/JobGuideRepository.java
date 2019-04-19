package com.mmt.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mmt.management.entity.JobGuide;

public interface JobGuideRepository extends JpaSpecificationExecutor<JobGuide>,JpaRepository<JobGuide, Long> {
}
