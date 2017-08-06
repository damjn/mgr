package com.mgr2.service;

import java.util.List;

import com.mgr2.dto.UserTaskDTO;
import com.mgr2.dto.UserTaskJoinDTO;
import com.mgr2.model.UserTask;

public interface UserTaskService {
	
	String markUserTaskDone(UserTaskDTO utDTO);
	UserTask getUserTaskByUserIdAndTaskName(String taskName,int user_id);
	String handle24hLoginTask(int user_id);
	String handleEmailConfirmationTask(String token);
	String sendEmailToConfirmUserEmail();
	String handleRecomendationLink(String token);
	String getRecomendationLink();
	List<UserTaskJoinDTO> getAllUserTasks();

}
