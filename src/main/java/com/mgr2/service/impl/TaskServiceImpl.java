package com.mgr2.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mgr2.dto.TaskDTO;
import com.mgr2.dto.convertion.TaskDTOConverter;
import com.mgr2.model.Task;
import com.mgr2.model.User;
import com.mgr2.model.UserTask;
import com.mgr2.repository.TaskRepository;
import com.mgr2.repository.UserRepository;
import com.mgr2.repository.UserTaskRepository;
import com.mgr2.service.TaskService;
import com.mgr2.service.UserService;

@Service("taskService")
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskDTOConverter taskDTOConverter;
	
	@Autowired
	UserTaskRepository userTaskRepository;

	@Override
	public String saveTask(TaskDTO tDTO) {
		Task t = taskDTOConverter.convertDTOtoModel(tDTO);
		taskRepository.save(t);
		
		
		List<User> allUsers = userRepository.findAll();
		for(User u : allUsers){
			UserTask ut = new UserTask();
			ut.setTask(t);
			ut.setUser(u);
			ut.setCreatedDate(new Date());
			ut.setDone(0);
			String token = UUID.randomUUID().toString();
			ut.setToken(token);
			userTaskRepository.save(ut);
//			u.getTasks().add(ut);
//			userRepository.save(u);
		}
		
		
		return "TASK ADDED";
		
	}
	
	

}
