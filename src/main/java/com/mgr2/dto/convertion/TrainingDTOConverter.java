package com.mgr2.dto.convertion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mgr2.dto.TrainingDTO;
import com.mgr2.model.Training;

public class TrainingDTOConverter {
	
	public static TrainingDTO convertModelToDTO(Training training){
		TrainingDTO tDTO = new TrainingDTO();
		tDTO.setId(training.getId());
		tDTO.setUserDTO(UserDTOConverter.convertModelToDTO(training.getUser()));
		tDTO.setName(training.getName());
		tDTO.setDescription(training.getDescription());
		tDTO.setPrice(training.getPrice());
		return tDTO;
	}
	
	public static Training convertDTOtoModel(TrainingDTO tDTO){
		Training t = new Training();
		t.setId(tDTO.getId());
		t.setUser(UserDTOConverter.convertDTOtoModel(tDTO.getUserDTO()));
		t.setName(tDTO.getName());
		t.setDescription(tDTO.getDescription());
		t.setPrice(tDTO.getPrice());
		return t;
	}
	
	public static List<TrainingDTO> convertListOfModelsToDTOList(List<Training> trainings) {
        List<TrainingDTO> tDTO = new ArrayList<>();
        for (Training t : trainings) {
            tDTO.add(convertModelToDTO(t));
        }
        return tDTO;
    }

}
