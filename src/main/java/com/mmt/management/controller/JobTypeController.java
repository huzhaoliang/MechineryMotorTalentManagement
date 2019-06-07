package com.mmt.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mmt.management.entity.JobType;
import com.mmt.management.service.JobTypeService;
import com.mmt.management.support.Helper;

@Controller
public class JobTypeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static int pageSize = 20;

    @Autowired
    private JobTypeService jobTypeService;

    @RequestMapping(value="/manage/jobType_list")
    public String list(Model model) {
        /*
    	System.out.println("++++++++jobType list++++++++++" + type);
        Page<JobType> jobTypes = jobTypeService.getJobTypes(type, pageNumber, pageSize);

        if(jobTypes != null) {
            model.addAttribute("jobTypes", jobTypes);
        }
        List<JobType> parentTypes = jobTypeService.getParentJobTypes();
        Map<Long, String> pTypeMap = new HashMap<Long, String>();
        for(JobType t:parentTypes) {
            pTypeMap.put(t.getId(), t.getType());
        }
        model.addAttribute("parentTypes", pTypeMap);
        model.addAttribute("type", type);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", jobTypes.getTotalPages());
        if(Helper.isSuperUser()){
            model.addAttribute("isSystemAdmin",true);
        }
        */
    	
    	List<JobType> level_one_jobtypes = jobTypeService.findAllLevelOneJobType();
    	List<JobType> level_two_jobtypes = jobTypeService.findAllLevelTwoJobType();
    	List<JobType> level_three_jobtypes = jobTypeService.findAllLevelThreeJobType();
    	
    	if(level_one_jobtypes != null) {
            model.addAttribute("level_one_jobtypes", level_one_jobtypes);
        }
    	
    	if(level_two_jobtypes != null) {
            model.addAttribute("level_two_jobtypes", level_two_jobtypes);
        }
    	
    	if(level_three_jobtypes != null) {
            model.addAttribute("level_three_jobtypes", level_three_jobtypes);
        }
    	
        return "manage/jobType_list";
    }

    @RequestMapping(value="/manage/jobType_add")
    public String add(Model model) {
        logger.info("++++++++JobType add++++++++++");
        List<JobType> parentTypes = jobTypeService.getParentJobTypes();
        if(parentTypes != null) {
            model.addAttribute("parentTypes", parentTypes);
        }
        if(Helper.isSuperUser()){
            model.addAttribute("isSystemAdmin",true);
        }
        return "manage/jobType_add";
    }

    @RequestMapping(value="/manage/jobType_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="jobTypeForm") JobType jobType) {
        logger.info("++++++++jobType save++++++++++");
        if(jobType.getFlag() == 1l) {
            jobType.setParentId(null);
        }
        jobTypeService.saveJobType(jobType);
        return "redirect:jobType_list";
    }

    @RequestMapping(value="/manage/jobType_update")
    public String update(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++jobType update++++++++++");
        JobType jobType = jobTypeService.getJobTypeById(id);
        if(jobType != null) {
            model.addAttribute("jobType", jobType);
        }
        List<JobType> parentTypes = jobTypeService.getParentJobTypes();
        if(parentTypes != null) {
            model.addAttribute("parentTypes", parentTypes);
        }
        if(Helper.isSuperUser()){
            model.addAttribute("isSystemAdmin",true);
        }
        return "manage/jobType_update";
    }

    @RequestMapping(value="/manage/jobType_delete")
    public String delete(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++jobType delete++++++++++"+id);
        List<JobType> list = jobTypeService.getJobTypeByParent(id);
        if(list == null || list.size() == 0){
            jobTypeService.deleteJobTypeById(id);
        }
        return "redirect:jobType_list?pageNumber=1";
    }
}
