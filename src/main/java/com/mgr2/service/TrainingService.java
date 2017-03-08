package com.mgr2.service;

import com.mgr2.model.Training;
import com.mgr2.model.User;

public interface TrainingService {
	public Training findTrainingByName(String name);
	public void saveTraining(Training training);
}
