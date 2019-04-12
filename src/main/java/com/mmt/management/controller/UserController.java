package com.mmt.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmt.management.entity.User;
import com.mmt.management.service.UserService;

@Controller
public class UserController {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/manage/user_list")
	public String list(Model model, @ModelAttribute(value="parentId") String parentId, @ModelAttribute(value="name") String name) {
		System.out.println("++++++++city list++++++++++" + parentId + name);
		Page<User> users = null;
		if(("-1".equals(parentId) || "".equals(parentId)) && "".equals(name)){
			users = userService.getUsers(1, 20);
		}else {
			users = userService.getUsersByQueries(name, 1, 20);
		}
		
		if(users != null) {
			model.addAttribute("users", users);
		}
		model.addAttribute("name", name);
		return "manage/user_list";
	}
	
	@RequestMapping(value="/manage/user_audit")
	public String delete(Model model, @ModelAttribute(value="id") Long id, @ModelAttribute(value="status") Integer status) {
		logger.info("++++++++update user status++++++++++"+id);
		userService.updateUserStatus(id, status);
		return "redirect:city_list";
	}
}
