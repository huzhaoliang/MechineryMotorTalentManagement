package com.mmt.management.controller;

import com.mmt.management.entity.JobType;
import com.mmt.management.service.JobTypeService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JobTypeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static int pageSize = 20;

    @Autowired
    private JobTypeService jobTypeService;

    @RequestMapping(value="/manage/jobType_list")
    public String list(Model model, @ModelAttribute(value="type") String type,
                       @ModelAttribute(value="pageNumber") int pageNumber) {
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
        return "manage/jobType_list";
    }

    @RequestMapping(value="/manage/jobType_add")
    public String add(Model model) {
        logger.info("++++++++JobType add++++++++++");
        List<JobType> parentTypes = jobTypeService.getParentJobTypes();
        if(parentTypes != null) {
            model.addAttribute("parentTypes", parentTypes);
        }
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
        return "manage/jobType_add";
    }

    @RequestMapping(value="/manage/jobType_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="jobTypeForm") JobType jobType) {
        logger.info("++++++++jobType save++++++++++");
        if(jobType.getFlag() == 1l) {
            jobType.setParentId(null);
        }
        jobTypeService.saveJobType(jobType);
        return "redirect:jobType_list?pageNumber=1";
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
