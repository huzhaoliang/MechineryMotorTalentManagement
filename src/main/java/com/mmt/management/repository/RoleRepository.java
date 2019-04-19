package com.mmt.management.repository;

import com.mmt.management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaSpecificationExecutor<Role>,JpaRepository<Role, Long> {
}
