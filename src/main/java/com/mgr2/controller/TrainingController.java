package com.mgr2.controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgr2.dto.ContentDTO;
import com.mgr2.dto.TrainingDTO;
import com.mgr2.dto.UserDTO;
import com.mgr2.dto.convertion.TrainingDTOConverter;
import com.mgr2.model.Category;
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
	
	@RequestMapping(value = "/courses/{category}",method = RequestMethod.GET)
    public @ResponseBody
    List<TrainingDTO> getCategoryCourses(@PathVariable String category){
        return trainingDTOConverter.convertListOfModelsToDTOList(trainingRepository.findByCategory(Category.valueOf(category)));
    }
	
	@RequestMapping(value = "/t_courses",method = RequestMethod.GET)
    public @ResponseBody
    List<TrainingDTO> getLoggedTrainerCourses(){
        return trainingService.getCoursesByLoggedTrainerId();
    }
	
	@RequestMapping(value = "/u_courses",method = RequestMethod.POST)
    public @ResponseBody
    List<TrainingDTO> getUserCourses(@RequestBody UserDTO uDTO){
        //return trainingDTOConverter.convertListOfModelsToDTOList(trainingRepository.findByUserEmail(email));
		return trainingService.getUserCourses(uDTO.getEmail());
    }
	
	@RequestMapping(value = "/course/{id}",method = RequestMethod.GET)
    public @ResponseBody TrainingDTO getCourse(@PathVariable("id") int courseId){
        return trainingService.findById(courseId);
    }
	
	@RequestMapping(value = "/course",method = RequestMethod.POST)
    public @ResponseBody String addCourse (@RequestBody TrainingDTO training, @RequestParam("user_id") int user_id){
       return trainingService.saveTraining(training, user_id);
    }
	
	@RequestMapping(value = "/course",method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody String editCourse (@RequestBody TrainingDTO training){
       return trainingService.updateTraining(training);
    }
	
	
	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCourse(@PathVariable("id") int courseId) {
		trainingRepository.delete(courseId);
		return "CONTENT DELETED";
	}

    @RequestMapping(value = "/course/categories", method = RequestMethod.GET)
    public @ResponseBody List<String> getCategories() {
       return Stream.of(Category.values())
                .map(Category::name)
                .collect(Collectors.toList());

    }

}
