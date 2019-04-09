package com.mmt.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mmt.entity.JobGuide;

public interface JobGuideRepository extends JpaRepository<JobGuide, Long> {
	@Modifying
	@Transactional
	@Query(value="delete from jobGuide where id=:id", nativeQuery = true)
	void deleteJobGuideById(@Param("id")Long id);
}
