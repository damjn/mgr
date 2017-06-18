package com.mgr2.service;

import java.util.List;
import java.util.Set;

import com.mgr2.dto.TrainingDTO;
import com.mgr2.model.Training;
import com.mgr2.model.User;

public interface TrainingService {
	public Training findTrainingByName(String name);
	public String saveTraining(Training training);
	public int findTrainingIdByName(String name);
	public List<TrainingDTO> getCoursesByLoggedTrainerId();
	public TrainingDTO findById(int id);
	public String saveTraining(TrainingDTO training, int user_id);
}
