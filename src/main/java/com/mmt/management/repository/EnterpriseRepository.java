package com.mmt.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mmt.management.entity.EnterpriseUser;

public interface EnterpriseRepository extends JpaRepository<EnterpriseUser, Long>{

}
