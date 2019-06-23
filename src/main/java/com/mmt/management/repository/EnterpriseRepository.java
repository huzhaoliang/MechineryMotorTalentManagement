package com.mmt.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mmt.management.entity.EnterpriseUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EnterpriseRepository extends JpaSpecificationExecutor<EnterpriseUser>,JpaRepository<EnterpriseUser, Long>{
    @Modifying
    @Transactional
    @Query(value="update enterprise_user set status=:status where id=:id", nativeQuery = true)
    void updateEnterpriseStatus(@Param("status")Long status, @Param("id")Long id);
}
