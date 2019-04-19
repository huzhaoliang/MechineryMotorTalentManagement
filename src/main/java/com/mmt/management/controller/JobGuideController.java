package com.mmt.management.controller;

import com.mmt.management.entity.Job;
import com.mmt.management.entity.JobGuide;
import com.mmt.management.entity.Menu;
import com.mmt.management.service.JobGuideService;
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
public class JobGuideController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JobGuideService jobGuideService;

    @RequestMapping(value="/manage/jobGuide_list")
    public String list(Model model, @ModelAttribute(value="title") String title) {
        System.out.println("++++++++jobGuide list++++++++++");
        Page<JobGuide> jobGuides = jobGuideService.getJobGuides(title,1, 12);

        if(jobGuides != null) {
            model.addAttribute("jobGuides", jobGuides);
        }
        model.addAttribute("title", title);
        return "manage/jobGuide_list";
    }

    @RequestMapping(value="/manage/jobGuide_add")
    public String add(Model model) {
        logger.info("++++++++jobGuide add++++++++++");
        return "manage/jobGuide_add";
    }

    @RequestMapping(value="/manage/jobGuide_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="jobGuideForm") JobGuide jobGuide) {
        logger.info("++++++++jobGuide save++++++++++");
        jobGuideService.saveJobGuide(jobGuide);
        return "redirect:jobGuide_list";
    }

    @RequestMapping(value="/manage/jobGuide_delete")
    public String delete(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++jobGuide delete++++++++++"+id);
        jobGuideService.deleteJobGuideById(id);
        return "redirect:jobGuide_list";
    }
}
