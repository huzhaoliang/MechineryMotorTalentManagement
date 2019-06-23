package com.mmt.management.controller;

import com.mmt.management.entity.City;
import com.mmt.management.entity.EnterpriseUser;
import com.mmt.management.service.CityService;
import com.mmt.management.service.EnterpriseService;
import com.mmt.management.support.Helper;
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
public class EnterpriseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private final static int pageSize = 20;
	
	@Autowired
	private EnterpriseService enterService;
	
	@RequestMapping(value="/manage/enter_list")
	public String list(Model model, @ModelAttribute(value="status") String status,
					   @ModelAttribute(value="name") String name,
					   @ModelAttribute(value="pageNumber") int pageNumber) {
		System.out.println("++++++++enterprise list++++++++++" + status + name);
		Page<EnterpriseUser> enters = enterService.getEnterprises(name, status, pageNumber, pageSize);
		
		if(enters != null) {
			model.addAttribute("enters", enters);
		}
		model.addAttribute("status", status);
		model.addAttribute("name", name);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPages", enters.getTotalPages());
		if(Helper.isSuperUser()){
			model.addAttribute("isSystemAdmin",true);
		}
		return "manage/enter_list";
	}

	@RequestMapping(value="/manage/enter_review")
	public String review(Model model, @ModelAttribute(value="id") Long id) {
		logger.info("++++++++enter review++++++++++");
		EnterpriseUser enter = enterService.getOne(id);
		if(enter != null) {
			model.addAttribute("enter", enter);
		}
		return "manage/enter_review";
	}

	@RequestMapping(value="/manage/enter_status_update")
	public String updateStatus(Model model, @ModelAttribute(value="id") Long id, @ModelAttribute(value="status") Long status) {
		logger.info("++++++++update enterprise status++++++++++"+id);
		enterService.updateEnterpriseStatus(status, id);
		return "redirect:enter_list?pageNumber=1";
	}
}
