package com.mmt.management.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmt.management.entity.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long>{

	@Query(value="select a.* from admin_user a where a.name=:name and a.password=:password", nativeQuery = true)
    AdminUser checkUserByNameAndPwd(@Param("name")String name, @Param("password")String password);
	
	@Query(value="select a.* from admin_user a where a.name=:name", nativeQuery = true)
    AdminUser checkUserByName(@Param("name")String name);
}
