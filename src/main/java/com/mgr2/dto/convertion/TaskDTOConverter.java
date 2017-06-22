package com.mgr2.dto.convertion;

import org.springframework.stereotype.Service;

import com.mgr2.dto.TaskDTO;
import com.mgr2.model.Task;

@Service("taskDTOConverter")
public class TaskDTOConverter {
	
	public Task convertDTOtoModel(TaskDTO tDTO){
		Task t = new Task();
		t.setId(tDTO.getId());
		t.setName(tDTO.getName());
		t.setPoints(tDTO.getPoints());
		t.setDescription(tDTO.getDescription());
		return t;
	}
	
	public TaskDTO convertModelToDTO(Task t){
		TaskDTO tDTO = new TaskDTO();
		tDTO.setId(t.getId());
		tDTO.setName(t.getName());
		tDTO.setPoints(t.getPoints());
		tDTO.setDescription(t.getDescription());
		return tDTO;
	}
	

}
