package com.mgr2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mgr2.dto.TrainingDTO;
import com.mgr2.model.Training;
import com.mgr2.model.User;
import com.mgr2.service.TrainingService;
import com.mgr2.service.UserService;
import com.mgr2.storage.StorageService;

@Controller
public class AddCourseController {

	@Autowired
	private UserService userService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private TrainingService trainingService;

	@RequestMapping(value = "/add_course", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		TrainingDTO trainingDTO = new TrainingDTO();
		modelAndView.addObject("trainingDTO", trainingDTO);
		modelAndView.setViewName("add_course");
		return modelAndView;
	}

	@RequestMapping(value = "/add_course", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid TrainingDTO trainingDTO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("else");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Training trainingExists = trainingService.findTrainingByName(trainingDTO.getName());
		if (trainingExists != null) {
			bindingResult.rejectValue("name", "error.trainingDTO",
					"There is already a training registered with the name provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("add_course");
		} else {
			storageService.init(); // do zmiany
			storageService.store(trainingDTO.getFile(), trainingDTO.getName());
			Training training = new Training();
			training.setDescritpion(trainingDTO.getDescription());
			training.setName(trainingDTO.getName());
			training.setUser(user);
			String path = storageService.load(trainingDTO.getFile().getOriginalFilename()).toString();
			System.out.println("sciezka" + path);
			training.setPath(path);
			training.setPrice(trainingDTO.getPrice());
			trainingService.saveTraining(training);
			modelAndView.addObject("trainingDTO", new TrainingDTO());
			modelAndView.addObject("successMessage", "Training has been added successfully");
			modelAndView.setViewName("add_course");
		}
		return modelAndView;
	}

}
