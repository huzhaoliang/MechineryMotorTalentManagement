package com.mmt.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmt.management.entity.JobType;

public interface JobTypeRepository extends JpaSpecificationExecutor<JobType>, JpaRepository<JobType, Long>{
    @Query(value="select a.* from job_type a where a.flag=1", nativeQuery = true)
    List<JobType> getParentJobTypes();

    @Query(value="select a.* from job_type a where a.parent_id=:parentId", nativeQuery = true)
    List<JobType> getJobTypeByParent(@Param("parentId")Long parentId);
    
    @Query(value="select a.* from job_type a where a.flag=1", nativeQuery = true)
    List<JobType> findAllLevelOneJobType();
    
    @Query(value="select a.* from job_type a where a.flag=2", nativeQuery = true)
    List<JobType> findAllLevelTwoJobType();
    
    @Query(value="select a.* from job_type a where a.flag=3", nativeQuery = true)
    List<JobType> findAllLevelThreeJobType();
}
