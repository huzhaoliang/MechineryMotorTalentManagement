package com.mmt.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mmt.management.support.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mmt.management.entity.City;
import com.mmt.management.service.CityService;

@Controller
public class CityController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private final static int pageSize = 20;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="/manage/city_list")
	public String list(Model model, @ModelAttribute(value="parentId") String parentId,
					   @ModelAttribute(value="name") String name,
					   @ModelAttribute(value="pageNumber") int pageNumber) {
		System.out.println("++++++++city list++++++++++" + parentId + name);
		Page<City> citys = cityService.getCitysByQueries(parentId, name, pageNumber, pageSize);
		
		if(citys != null) {
			model.addAttribute("citys", citys);
		}
		List<City> provinces = cityService.getProvinces();
		Map<Long, String> provinceMap = new HashMap<Long, String>();
		for(City city:provinces) {
			provinceMap.put(city.getId(), city.getName());
		}
		model.addAttribute("provinces", provinceMap);
		
		if(provinces != null) {
			model.addAttribute("provs", provinces);
		}
		model.addAttribute("parentId", parentId);
		model.addAttribute("name", name);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPages", citys.getTotalPages());
		if(Helper.isSuperUser()){
			model.addAttribute("isSystemAdmin",true);
		}
		return "manage/city_list";
	}
	
	@RequestMapping(value="/manage/city_add")
	public String add(Model model) {
		logger.info("++++++++city add++++++++++");
		List<City> provinces = cityService.getProvinces();
		if(provinces != null) {
			model.addAttribute("provinces", provinces);
		}
		if(Helper.isSuperUser()){
			model.addAttribute("isSystemAdmin",true);
		}
		return "manage/city_add";
	}
	
	@RequestMapping(value="/manage/city_save", method=RequestMethod.POST)
	public String save(@ModelAttribute(value="cityForm") City city) {
		logger.info("++++++++city save++++++++++");
		if(city.getFlag() == 1l) {
			city.setParentId(null);
		}
		cityService.saveCity(city);
		return "redirect:city_list?pageNumber=1";
	}
	
	@RequestMapping(value="/manage/city_update")
	public String update(Model model, @ModelAttribute(value="id") Long id) {
		logger.info("++++++++city update++++++++++");
		City city = cityService.getOneCity(id);
		if(city != null) {
			model.addAttribute("city", city);
		}
		List<City> provinces = cityService.getProvinces();
		if(provinces != null) {
			model.addAttribute("provinces", provinces);
		}
		if(Helper.isSuperUser()){
			model.addAttribute("isSystemAdmin",true);
		}
		return "manage/city_update";
	}
	
	@RequestMapping(value="/manage/city_delete")
	public String delete(Model model, @ModelAttribute(value="id") Long id) {
		logger.info("++++++++city delete++++++++++"+id);
		List<City> list = cityService.getCityByParent(id);
		if(list == null || list.size() == 0){
			cityService.deleteCityById(id);
		}
		return "redirect:city_list?pageNumber=1";
	}
}
