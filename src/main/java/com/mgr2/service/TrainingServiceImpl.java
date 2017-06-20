package com.mgr2.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mgr2.dto.TrainingDTO;
import com.mgr2.dto.convertion.TrainingDTOConverter;
import com.mgr2.model.Training;
import com.mgr2.model.User;
import com.mgr2.repository.TrainingRepository;
import com.mgr2.repository.UserRepository;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TrainingDTOConverter trainingDTOConverter;
	
	@Override
	public String saveTraining(Training training) { // pozmieniac na DTO
		//training.setAccepted(1);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    User user = userRepository.findByEmail(name);
	    training.setUser(user);
	    System.out.println("opis: " + training.getDescription());
		trainingRepository.save(training);
		//System.out.println("kursy stworzone przez tego usera: " + user.getCreated_trainings());
		return "Training Saved";
	}

	@Override
	public Training findTrainingByName(String name) {
		return trainingRepository.findByName(name);
	}

	@Override
	public int findTrainingIdByName(String name) {
		return trainingRepository.findTrainingIdByName(name);
	}

	@Override
	public List<TrainingDTO> getCoursesByLoggedTrainerId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    User user = userRepository.findByEmail(name);
	    List<Training> trainerCourses = trainingRepository.findByUserId(user.getId());
		return trainingDTOConverter.convertListOfModelsToDTOList(trainerCourses);
	}

	@Override
	public TrainingDTO findById(int id) {
		return trainingDTOConverter.convertModelToDTO(trainingRepository.findById(id));
	}

	@Override
	public String saveTraining(TrainingDTO tDTO, int user_id) {
		Training tr = trainingDTOConverter.convertDTOtoModel(tDTO);
		User user = userRepository.findById(user_id);
		tr.setUser(user);
		trainingRepository.save(tr);
		return "TRAINIG SAVED";
	}

	@Override
	public List<TrainingDTO> getUserCourses(String email) {
		User user = userRepository.findByEmail(email);
		System.out.println("na przyklad mail: " + user.getEmail() + "i nazwa: " + user.getName());
		Set<Training> userTrainings = user.getTrainings();
		return trainingDTOConverter.convertSetOfModelsToDTOList(userTrainings);
	}

	@Override
	public String updateTraining(TrainingDTO tDTO) {
		Training training = trainingRepository.findById(tDTO.getId());
		training = trainingDTOConverter.convertDTOtoModel(tDTO, training);
		trainingRepository.save(training);
		return "TRAINING UPDATED";
	}

}
