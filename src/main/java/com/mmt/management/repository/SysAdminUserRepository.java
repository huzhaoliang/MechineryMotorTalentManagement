package com.mmt.management.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmt.management.entity.SysAdminUser;

public interface SysAdminUserRepository extends JpaRepository<SysAdminUser, Long>{

	@Query(value="select a.* from admin_user a where a.name=:name and a.password=:password", nativeQuery = true)
	SysAdminUser checkUserByNameAndPwd(@Param("name")String name, @Param("password")String password);
	
	@Query(value="select a.* from admin_user a where a.name=:name", nativeQuery = true)
	SysAdminUser checkUserByName(@Param("name")String name);
}
