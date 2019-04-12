package com.mmt.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmt.management.entity.User;
import com.mmt.management.service.UserService;

@Controller
@RequestMapping(value="/")
public class SignUpController 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/signup")
	public String signup(Model model) 
	{
		logger.info("++++++++Sign up++++++++++");
		//当前登录的用户
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getUserByName(name);
		model.addAttribute("user", user);
		return "signup";
	}
	
}
