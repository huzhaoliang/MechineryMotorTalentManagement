package com.mmt.management.controller;

import com.mmt.management.entity.JobGuide;
import com.mmt.management.service.JobGuideService;
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

import java.util.Date;

@Controller
public class JobGuideController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static int pageSize = 20;

    @Autowired
    private JobGuideService jobGuideService;

    @RequestMapping(value="/manage/jobGuide_list")
    public String list(Model model, @ModelAttribute(value="title") String title,
                       @ModelAttribute(value="pageNumber") int pageNumber) {
        System.out.println("++++++++jobGuide list++++++++++");
        Page<JobGuide> jobGuides = jobGuideService.getJobGuides(title,pageNumber, pageSize);

        if(jobGuides != null) {
            model.addAttribute("jobGuides", jobGuides);
        }
        model.addAttribute("title", title);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", jobGuides.getTotalPages());
        if(Helper.isSuperUser()){
            model.addAttribute("isSystemAdmin",true);
        }
        return "manage/jobGuide_list";
    }

    @RequestMapping(value="/manage/jobGuide_view")
    public String view(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++jobGuide display++++++++++");
        JobGuide jobGuide = jobGuideService.getOneJobGuide(id);
        model.addAttribute("jobGuide", jobGuide);
        if(Helper.isSuperUser()){
            model.addAttribute("isSystemAdmin",true);
        }
        return "manage/jobGuide_view";
    }

    @RequestMapping(value="/manage/jobGuide_add")
    public String add(Model model) {
        logger.info("++++++++jobGuide add++++++++++");
        if(Helper.isSuperUser()){
            model.addAttribute("isSystemAdmin",true);
        }
        return "manage/jobGuide_add";
    }

    @RequestMapping(value="/manage/jobGuide_update")
    public String update(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++jobGuide update++++++++++");
        JobGuide jobGuide = jobGuideService.getOneJobGuide(id);
        model.addAttribute("jobGuide", jobGuide);
        if(Helper.isSuperUser()){
            model.addAttribute("isSystemAdmin",true);
        }
        return "manage/jobGuide_update";
    }

    @RequestMapping(value="/manage/jobGuide_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="jobGuideForm") JobGuide jobGuide) {
        logger.info("++++++++jobGuide save++++++++++");
        jobGuide.setPublishTime(new Date());
        jobGuideService.saveJobGuide(jobGuide);
        return "redirect:jobGuide_list?pageNumber=1";
    }

    @RequestMapping(value="/manage/jobGuide_delete")
    public String delete(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++jobGuide delete++++++++++"+id);
        jobGuideService.deleteJobGuideById(id);
        return "redirect:jobGuide_list?pageNumber=1";
    }
}
