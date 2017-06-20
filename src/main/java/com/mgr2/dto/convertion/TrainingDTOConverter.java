package com.mgr2.dto.convertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr2.dto.TrainingDTO;
import com.mgr2.model.Training;

@Service("trainingDTOConverter")
public class TrainingDTOConverter {
	
	@Autowired
	UserDTOConverter userDTOConverter;
	
	@Autowired
	ContentDTOConverter contentDTOConverter;
	
	public TrainingDTO convertModelToDTO(Training training){
		TrainingDTO tDTO = new TrainingDTO();
		tDTO.setId(training.getId());
		tDTO.setUserDTO(userDTOConverter.convertModelToDTO(training.getUser()));
		tDTO.setName(training.getName());
		tDTO.setDescription(training.getDescription());
		tDTO.setContentList(contentDTOConverter.convertSetOfModelsToDTOList(training.getContent()));
		tDTO.setPrice(training.getPrice());
		tDTO.setCategory(training.getCategory());
		tDTO.setAverageRate(training.getAverageRate());
		return tDTO;
	}
	
	public Training convertDTOtoModel(TrainingDTO tDTO){
		Training t = new Training();
		t.setId(tDTO.getId());
		//t.setUser(userDTOConverter.convertDTOtoModel(tDTO.getUserDTO())); chyba cza wywalic
		t.setName(tDTO.getName());
		t.setDescription(tDTO.getDescription());
		// t.setContent(); chyba nie potrzebne
		t.setPrice(tDTO.getPrice());
		t.setCategory(tDTO.getCategory());
		t.setAverageRate(tDTO.getAverageRate());
		return t;
	}
	
	public Training convertDTOtoModel(TrainingDTO tDTO, Training t){
		t.setId(tDTO.getId());
		//t.setUser(userDTOConverter.convertDTOtoModel(tDTO.getUserDTO())); chyba cza wywalic
		t.setName(tDTO.getName());
		t.setDescription(tDTO.getDescription());
		// t.setContent(); chyba nie potrzebne
		t.setPrice(tDTO.getPrice());
		t.setCategory(tDTO.getCategory());
		t.setAverageRate(tDTO.getAverageRate());
		return t;
	}
	
	public List<TrainingDTO> convertListOfModelsToDTOList(List<Training> trainings) {
        List<TrainingDTO> tDTO = new ArrayList<>();
        for (Training t : trainings) {
            tDTO.add(convertModelToDTO(t));
        }
        return tDTO;
    }
	
	public List<TrainingDTO> convertSetOfModelsToDTOList(Set<Training> trainings) {
        List<TrainingDTO> tDTO = new ArrayList<>();
        for (Training t : trainings) {
            tDTO.add(convertModelToDTO(t));
        }
        return tDTO;
    }

}
