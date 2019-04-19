package com.mmt.management.service;

import com.mmt.management.entity.Menu;
import com.mmt.management.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    Role saveRole(Role role);

    void deleteRoleById(Long id);

    Page<Role> getRoleByQueries(String code, String name, int pageNumber, int pageSize);
}
