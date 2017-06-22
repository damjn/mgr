package com.mgr2.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr2.dto.UserTaskDTO;
import com.mgr2.model.Task;
import com.mgr2.model.User;
import com.mgr2.model.UserTask;
import com.mgr2.model.UserTaskId;
import com.mgr2.repository.TaskRepository;
import com.mgr2.repository.UserRepository;
import com.mgr2.repository.UserTaskRepository;
import com.mgr2.service.UserTaskService;

@Service("userTaskServiceImpl")
public class UserTaskServiceImpl implements UserTaskService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	UserTaskRepository userTaskRepository;

	@Override
	public String markUserTaskDone(UserTaskDTO utDTO) {
		User user = userRepository.findById(utDTO.getUser_id());
		Task task = taskRepository.findByName(utDTO.getTaskname());
		UserTaskId id = new UserTaskId();
		id.setTask(task);
		id.setUser(user);
		UserTask ut = userTaskRepository.findOne(id);
		ut.setDone(1);
		ut.setCreatedDate(new Date());
		userTaskRepository.save(ut);
		return "TASK DONE";
	}
	
	

}
