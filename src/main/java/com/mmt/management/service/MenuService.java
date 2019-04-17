package com.mmt.management.service;

import com.mmt.management.entity.Menu;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuService {

    Menu saveMenu(Menu menu);

    void deleteMenus(List<Menu> menus);

    Page<Menu> getMenusByQueries(String code, String name, int pageNumber, int pageSize);

    void deleteMenuById(Long id);

    List<Menu> getTopMenus();
}
