package com.mgr2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mgr2.configuration.MyUserPrincipal;
import com.mgr2.dto.TaskDTO;
import com.mgr2.dto.TrainingDTO;
import com.mgr2.dto.UserTaskDTO;
import com.mgr2.service.TaskService;
import com.mgr2.service.UserTaskService;

@Controller
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserTaskService userTaskService;
	
	@RequestMapping(value = "/task",method = RequestMethod.POST)
    public @ResponseBody String addTask (@RequestBody TaskDTO tDTO){
       return taskService.saveTask(tDTO);
    }
	
	@RequestMapping(value = "/taskDone",method = RequestMethod.POST)
    public @ResponseBody String taskDone (@RequestBody UserTaskDTO utDTO){
       return userTaskService.markUserTaskDone(utDTO);
    }
	
	@RequestMapping(value = "/sendEmail",method = RequestMethod.GET)
    public @ResponseBody String taskDone (){
       return userTaskService.sendEmailToConfirmUserEmail();
    }
	
	@RequestMapping(value = "/emailConfirm", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam("token") String token) {
		userTaskService.handleEmailConfirmationTask(token);
		ModelAndView model = new ModelAndView();
		MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addObject("user", user);
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value = "/getRecomendationLink",method = RequestMethod.GET)
    public @ResponseBody String getRecomendationLink (){
       return userTaskService.getRecomendationLink();
    }
	
	

}
