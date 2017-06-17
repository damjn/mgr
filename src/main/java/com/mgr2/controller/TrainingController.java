package com.mgr2.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgr2.dto.ContentDTO;
import com.mgr2.dto.TrainingDTO;
import com.mgr2.dto.convertion.TrainingDTOConverter;
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
    List<TrainingDTO> getTrainerCourses(){
        return trainingService.getCoursesByLoggedTrainerId();
    }
	
	@RequestMapping(value = "/t_courses/{id}",method = RequestMethod.GET)
    public @ResponseBody TrainingDTO getTrainerCourse(@PathVariable("id") int courseId){
        return trainingService.findById(courseId);
    }

}
