package com.mmt.management.service.impl;

import com.mmt.management.entity.Menu;
import com.mmt.management.repository.MenuRepository;
import com.mmt.management.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu saveMenu(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public void deleteMenus(List<Menu> menus) {
        menuRepository.deleteInBatch(menus);
    }

    @Override
    public Page<Menu> getMenusByQueries(String code, String name, int pageNumber, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Menu> permissions = null;
        if("".equals(code) && "".equals(name)){
            permissions = menuRepository.findAll(request);
        }else{
            Specification<Menu> spec = new Specification<Menu>() {
                public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
            permissions = menuRepository.findAll(spec, request);
        }
        return permissions;
    }

    @Override
    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public List<Menu> getTopMenus() {
        return menuRepository.findFirstLevelMenus();
    }
}
