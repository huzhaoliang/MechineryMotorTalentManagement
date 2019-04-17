package com.mmt.management.controller;

import com.mmt.management.entity.Menu;
import com.mmt.management.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @RequestMapping(value="/manage/menu_list")
    public String list(Model model, @ModelAttribute(value="code") String code, @ModelAttribute(value="name") String name) {
        System.out.println("++++++++menu list++++++++++" + name);
        Page<Menu> menus = menuService.getMenusByQueries(code, name, 1, 12);

        if(menus != null) {
            model.addAttribute("menus", menus);
        }

        List<Menu> topMenus = menuService.getTopMenus();
        Map<Long, String> menuMap = new HashMap<Long, String>();
        for(Menu menu:topMenus) {
            menuMap.put(menu.getId(), menu.getName());
        }
        model.addAttribute("topMenuMap", menuMap);

        model.addAttribute("code", name);
        model.addAttribute("name", name);
        return "manage/menu_list";
    }

    @RequestMapping(value="/manage/permission_add")
    public String add(Model model) {
        logger.info("++++++++permission add++++++++++");
        List<Menu> topMenus = menuService.getTopMenus();
        if(topMenus != null) {
            model.addAttribute("topMenus", topMenus);
        }
        return "manage/menu_add";
    }

    @RequestMapping(value="/manage/menu_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="menuForm") Menu menu) {
        logger.info("++++++++menu save++++++++++");
        menuService.saveMenu(menu);
        return "redirect:menu_list";
    }

    @RequestMapping(value="/manage/menu_delete")
    public String delete(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++menu delete++++++++++"+id);
        menuService.deleteMenuById(id);
        return "redirect:menu_list";
    }
}