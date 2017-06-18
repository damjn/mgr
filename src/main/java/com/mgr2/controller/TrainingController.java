package com.mgr2.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgr2.dto.ContentDTO;
import com.mgr2.dto.TrainingDTO;
import com.mgr2.dto.convertion.TrainingDTOConverter;
import com.mgr2.model.Training;
import com.mgr2.repository.TrainingRepository;
import com.mgr2.service.TrainingService;


@Controller
public class TrainingController {
	
	@Autowired
	TrainingService trainingService;
	
	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	TrainingDTOConverter trainingDTOConverter;
	
	@RequestMapping(value = "/courses",method = RequestMethod.GET)
    public @ResponseBody
    List<TrainingDTO> getAllCourses(){
        return trainingDTOConverter.convertListOfModelsToDTOList(trainingRepository.findAll());
    }
	
	@RequestMapping(value = "/t_courses",method = RequestMethod.GET)
    public @ResponseBody
    List<TrainingDTO> getLoggedTrainerCourses(){
        return trainingService.getCoursesByLoggedTrainerId();
    }
	
	@RequestMapping(value = "/course/{id}",method = RequestMethod.GET)
    public @ResponseBody TrainingDTO getCourse(@PathVariable("id") int courseId){
        return trainingService.findById(courseId);
    }
	
	@RequestMapping(value = "/course",method = RequestMethod.POST)
    public @ResponseBody String addCourse (@RequestBody TrainingDTO training, @RequestParam("user_id") int user_id){
       return trainingService.saveTraining(training, user_id);
    }
	
	@RequestMapping(value = "/course",method = RequestMethod.PUT)
    public @ResponseBody String editCourse (@RequestBody TrainingDTO training){
       return trainingService.saveTraining(training, training.getUserDTO().getId()); // ewentualnie napisac metode bez tego id bo to jest troche bez sensu
    }
	
	
	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteContent(@PathVariable("id") int courseId) {
		trainingRepository.delete(courseId);
		return "CONTENT DELETED";
	}

}
