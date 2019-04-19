package com.mmt.management.service.impl;

import com.mmt.management.entity.Menu;
import com.mmt.management.entity.Role;
import com.mmt.management.repository.RoleRepository;
import com.mmt.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role saveRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Page<Role> getRoleByQueries(String code, String name, int pageNumber, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Role> roles = null;
        if("".equals(code) && "".equals(name)){
            roles = roleRepository.findAll(request);
        }else{
            Specification<Role> spec = new Specification<Role>() {
                public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate p = null;
                    if(!"".equals(code)){
                        Path<String> codeAttribute = root.get("code");
                        Predicate p1 = cb.like(codeAttribute, "%"+code+"%");
                        p = cb.and(p1);
                        if(!"".equals(name)) {
                            Path<String> nameAttribute = root.get("name");
                            Predicate p2 = cb.like(nameAttribute, "%"+name+"%");
                            p = cb.and(p1, p2);
                        }
                    }else{
                        if(!"".equals(name)) {
                            Path<String> nameAttribute = root.get("name");
                            Predicate p2 = cb.like(nameAttribute, "%"+name+"%");
                            p = cb.and(p2);
                        }
                    }
                    return p;
                }
            };
            roles = roleRepository.findAll(spec, request);
        }
        return roles;
    }
}
