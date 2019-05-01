package com.mmt.management.repository;

import com.mmt.management.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mmt.management.entity.JobType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobTypeRepository extends JpaSpecificationExecutor<JobType>, JpaRepository<JobType, Long>{
    @Query(value="select a.* from job_type a where a.flag=1", nativeQuery = true)
    List<JobType> getParentJobTypes();

    @Query(value="select a.* from job_type a where a.parent_id=:parentId", nativeQuery = true)
    List<JobType> getJobTypeByParent(@Param("parentId")Long parentId);
}
