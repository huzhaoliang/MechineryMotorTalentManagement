package com.mmt.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmt.management.service.AdminUserService;

@Controller
public class MainController {

	@Autowired
	private AdminUserService userService;

	@RequestMapping(value="/manage/main")
	public String main(Model model) {
		System.out.println("++++++to main.html+++++");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		System.out.println("username is" + username);
		if("admin".equals(username)){
			model.addAttribute("isSystemAdmin",true);
		}
		return "manage/main";
	}

	@RequestMapping(value="/manage/top")
	public String top() {
		System.out.println("++++++to top.html+++++");
		return "manage/top";
	}

	@RequestMapping(value="/manage/left")
	public String left(Model model) {
		System.out.println("++++++to left.html+++++");
		return "manage/left";
	}
	
	
}
