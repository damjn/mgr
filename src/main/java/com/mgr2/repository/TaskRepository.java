package com.mgr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgr2.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	Task findByName(String name);

}
