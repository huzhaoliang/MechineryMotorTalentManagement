package com.mmt.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController 
{
	@RequestMapping(value = "/index")
	public String index() 
	{
		System.out.println("++++++to index.html+++++");
		return "index";
	}
	
	@RequestMapping(value = "/")
	public String home() 
	{
		System.out.println("++++++to index.html+++++");
		return "index";
	}
	

	
}
