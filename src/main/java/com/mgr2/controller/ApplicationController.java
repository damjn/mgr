package com.mgr2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {
	
	@RequestMapping(value = "/trainer_page", method = RequestMethod.GET)
	public ModelAndView trainerPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("trainer_page");
		return model;
	}
	
//	@RequestMapping(value = "/trainer_course", method = RequestMethod.GET)
//	public ModelAndView trainerCourse() {
//		ModelAndView model = new ModelAndView();
//		model.setViewName("trainer_course");
//		return model;
//	}

}
