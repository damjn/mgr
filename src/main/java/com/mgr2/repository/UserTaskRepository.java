package com.mgr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgr2.model.UserTask;
import com.mgr2.model.UserTaskId;

public interface UserTaskRepository extends JpaRepository<UserTask, UserTaskId>{
	
	UserTask findByToken(String token);
}
