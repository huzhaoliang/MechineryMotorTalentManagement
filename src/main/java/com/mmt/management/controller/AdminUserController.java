package com.mmt.management.controller;

import com.mmt.management.entity.AdminUser;
import com.mmt.management.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminUserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static int pageSize = 20;

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value="/manage/adminUser_list")
    public String list(Model model, @ModelAttribute(value="name") String name,
                       @ModelAttribute(value="pageNumber") int pageNumber) {
        System.out.println("++++++++city list++++++++++" + name);
        Page<AdminUser> users = adminUserService.getAdminUsersByQueries(name, pageNumber, pageSize);

        if(users != null) {
            model.addAttribute("users", users);
        }

        model.addAttribute("name", name);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", users.getTotalPages());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if("admin".equals(username)){
            model.addAttribute("isSystemAdmin",true);
        }
        return "manage/adminUser_list";
    }

    @RequestMapping(value="/manage/adminUser_add")
    public String add(Model model) {
        logger.info("++++++++adminUser add++++++++++");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if("admin".equals(username)){
            model.addAttribute("isSystemAdmin",true);
        }
        return "manage/adminUser_add";
    }

    @RequestMapping(value="/manage/adminUser_update")
    public String update(Model model) {
        logger.info("++++++++adminUser update++++++++++");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if("admin".equals(username)){
            model.addAttribute("isSystemAdmin",true);
        }
        AdminUser adminUser = adminUserService.checkUserByName(username);
        if(adminUser != null) {
            model.addAttribute("adminUser", adminUser);
        }
        return "manage/adminUser_update";
    }

    @RequestMapping(value="/manage/adminUser_savepwd", method=RequestMethod.POST)
    public String updatePwd(@ModelAttribute(value="adminUserForm") AdminUser adminUser) {
        logger.info("++++++++adminUser update pwd++++++++++");
        if(adminUser.getPassword() == null || "".equals(adminUser.getPassword())){
            //default pwd
            adminUser.setPassword("123456");
        }
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        adminUser.setPassword(encoder.encode(adminUser.getPassword().trim()));
        adminUserService.saveAdminUser(adminUser);
        return "manage/login";
    }

    @RequestMapping(value="/manage/adminUser_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="adminUserForm") AdminUser adminUser) {
        logger.info("++++++++adminUser save++++++++++");
        if(adminUser.getPassword() == null || "".equals(adminUser.getPassword())){
            //default pwd
            adminUser.setPassword("123456");
        }
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        adminUser.setPassword(encoder.encode(adminUser.getPassword().trim()));
        adminUserService.saveAdminUser(adminUser);
        return "redirect:adminUser_list?pageNumber=1";
    }

    @RequestMapping(value="/manage/adminUser_delete")
    public String delete(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++adminUser delete++++++++++"+id);
        adminUserService.deleteAdminUserById(id);
        return "redirect:adminUser_list?pageNumber=1";
    }
}
