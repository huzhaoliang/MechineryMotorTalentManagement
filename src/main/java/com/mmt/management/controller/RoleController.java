package com.mmt.management.controller;

import com.mmt.management.entity.Role;
import com.mmt.management.service.RoleService;
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
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/manage/role_list")
    public String list(Model model, @ModelAttribute(value="code") String code, @ModelAttribute(value="name") String name) {
        System.out.println("++++++++role list++++++++++" + name);
        Page<Role> roles = roleService.getRoleByQueries(code, name, 1, 12);

        if(roles != null) {
            model.addAttribute("roles", roles);
        }
        model.addAttribute("code", name);
        model.addAttribute("name", name);
        return "manage/menu_list";
    }

    @RequestMapping(value="/manage/role_add")
    public String add(Model model) {
        logger.info("++++++++role add++++++++++");
        return "manage/role_add";
    }

    @RequestMapping(value="/manage/role_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="roleForm") Role role) {
        logger.info("++++++++role save++++++++++");
        roleService.saveRole(role);
        return "redirect:role_list";
    }

    @RequestMapping(value="/manage/role_delete")
    public String delete(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++role delete++++++++++"+id);
        roleService.deleteRoleById(id);
        return "redirect:role_list";
    }
}
