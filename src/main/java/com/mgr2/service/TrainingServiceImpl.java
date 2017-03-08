package com.mgr2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr2.model.Training;
import com.mgr2.model.User;
import com.mgr2.repository.TrainingRepository;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	TrainingRepository trainingRepository;
	
	@Override
	public void saveTraining(Training training) {
		training.setAccepted(1);
		trainingRepository.save(training);
	}

	@Override
	public Training findTrainingByName(String name) {
		return trainingRepository.findByName(name);
	}

}
