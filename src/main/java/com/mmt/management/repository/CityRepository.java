package com.mmt.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mmt.management.entity.City;

public interface CityRepository extends JpaSpecificationExecutor<City>, JpaRepository<City, Long> {
	@Query(value="select a.* from city a where a.flag=1", nativeQuery = true)
	List<City> findProvinces();
	
	@Modifying
	@Transactional
	@Query(value="delete from city where id=:id", nativeQuery = true)
	void deleteCityById(@Param("id")Long id);

	@Query(value="select a.* from city a where a.parent_id=:parentId", nativeQuery = true)
	List<City> getCityByParent(@Param("parentId")Long parentId);
}
