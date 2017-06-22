package com.mgr2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	

}
