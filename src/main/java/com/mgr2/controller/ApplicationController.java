package com.mgr2.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mgr2.configuration.MyUserPrincipal;

@Controller
public class ApplicationController {
	
	@RequestMapping(value = "/trainer_page", method = RequestMethod.GET)
	public ModelAndView trainerPage() {
		ModelAndView model = new ModelAndView();
		MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addObject("user", user);
		model.setViewName("trainer_page");
		return model;
	}
	
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public ModelAndView videoPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("video");
		return model;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexPage() {
		ModelAndView model = new ModelAndView();
		MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addObject("user", user);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/index_l", method = RequestMethod.GET)
	public ModelAndView indexLoggedPage() {
		ModelAndView model = new ModelAndView();
		MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addObject("user", user);
		model.setViewName("loggedIndex");
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
		model.addObject("id",courseId);
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
