package com.mmt.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mmt.entity.EnterpriseUser;

public interface EnterpriseRepository extends JpaRepository<EnterpriseUser, Long>{

}
