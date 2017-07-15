package com.mgr2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mgr2.configuration.MyUserPrincipal;
import com.mgr2.dto.ContentDTO;
import com.mgr2.service.ContentService;

@Controller
public class ApplicationController {
	
	@Autowired
	ContentService contentService;
	
	@RequestMapping(value = "/index_l", method = RequestMethod.GET)
	public ModelAndView indexLoggedPage() {
		ModelAndView model = new ModelAndView();
		MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addObject("user", user);
		model.setViewName("loggedIndex");
		return model;
	}
	
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public ModelAndView videoPage(@RequestParam("name") String training_name) {
		ContentDTO first = contentService.getFirstContent(training_name);
		ModelAndView model = new ModelAndView();
		model.addObject("t_name", training_name);
		model.addObject("first", first);
		//List<ContentDTO> courseContent = contentService.getCourseContent(1);
		//model.addObject("courses",courseContent);
		model.setViewName("video");
		return model;
	}
	
	@RequestMapping(value = "/video2", method = RequestMethod.GET)
	public ModelAndView videoPage() {
		ModelAndView model = new ModelAndView();
		//List<ContentDTO> courseContent = contentService.getCourseContent(1);
		//model.addObject("courses",courseContent);
		model.setViewName("video2");
		return model;
	}

    @RequestMapping(value = "/trainer_page", method = RequestMethod.GET)
    public ModelAndView trainerPage() {
        ModelAndView model = new ModelAndView();
        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addObject("user", user);
        model.setViewName("trainer_page");
        return model;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView();

        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
        !(SecurityContextHolder.getContext().getAuthentication()
                instanceof AnonymousAuthenticationToken)) {

            MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addObject("user", user);
        }
        model.setViewName("index");
        return model;
    }


    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ModelAndView coursePage() {
        ModelAndView model = new ModelAndView();
        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addObject("user", user);
        model.setViewName("course");
        return model;
    }

    @RequestMapping(value = "/courseDetails/{id}", method = RequestMethod.GET)
    public ModelAndView courseDetailsPage(@PathVariable("id") int courseId) {
        ModelAndView model = new ModelAndView();
        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addObject("user", user);
        model.addObject("id", courseId);
        model.setViewName("coursePage");
        return model;
    }

//	@RequestMapping(value = "/trainer_course", method = RequestMethod.GET)
//	public ModelAndView trainerCourse() {
//		ModelAndView model = new ModelAndView();
//		model.setViewName("trainer_course");
//		return model;
//	}

}
