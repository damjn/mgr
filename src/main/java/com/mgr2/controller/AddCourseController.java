package com.mgr2.controller;

import java.util.ArrayList;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mgr2.dto.ContentDTO;
import com.mgr2.dto.TrainingDTO;
import com.mgr2.model.Content;
import com.mgr2.model.Training;
import com.mgr2.model.User;
import com.mgr2.service.ContentService;
import com.mgr2.service.TrainingService;
import com.mgr2.service.UserService;
import com.mgr2.storage.StorageService;


//DO WYWALENIA
@Controller
public class AddCourseController {

	@Autowired
	private UserService userService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private TrainingService trainingService;

	@Autowired
	private ContentService contentService;

	/*@RequestMapping(value = "/add_course", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		TrainingDTO trainingDTO = new TrainingDTO();
		ContentDTO content1 = new ContentDTO();
		ArrayList<ContentDTO> contentList = trainingDTO.getContentList();
		contentList.add(new ContentDTO());
		contentList.add(new ContentDTO());
		contentList.add(new ContentDTO());
		trainingDTO.setContentList(contentList);
		modelAndView.addObject("trainingDTO", trainingDTO);
		modelAndView.setViewName("add_course");
		return modelAndView;
	}*/

	/*@RequestMapping(value = "/add_course", method = RequestMethod.POST)
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
			ArrayList<ContentDTO> conDTOList = new ArrayList<>();
			HashSet<Content> conSet = new HashSet<>();
			conDTOList = trainingDTO.getContentList();
			for (ContentDTO contentDTO : conDTOList) {
				System.out.println("XDD");
				if (contentDTO.getDescription() == null || contentDTO.getDescription().isEmpty()) {
					System.out.println("jak gowno");
					break; // slabe to jest jak gowno
				}
				String path = storageService.store(contentDTO.getFile(), trainingDTO.getName());
				Content content = new Content();
				content.setDescription(contentDTO.getDescription());
				content.setOrder(contentDTO.getOrderNR());
				// String path =
				// storageService.load(contentDTO.getFile().getOriginalFilename()).toString();
				content.setPath(path);
				contentService.saveContent(content);
				conSet.add(content);
			}
			Training training = new Training();
			training.setDescription(trainingDTO.getDescription());
			training.setName(trainingDTO.getName());
			training.setUser(user);
			training.setPrice(trainingDTO.getPrice());
			training.setContent(conSet);
			trainingService.saveTraining(training);
			modelAndView.addObject("trainingDTO", new TrainingDTO());
			modelAndView.addObject("successMessage", "Training has been added successfully");
			modelAndView.setViewName("add_course"); // jakis inny view trzeba
													// ustawiac chyba
		}
		return modelAndView;
	}*/

	@RequestMapping(value = "/add_course2", method = RequestMethod.GET)
	public ModelAndView create_course() {
		ModelAndView model = new ModelAndView();
		model.setViewName("add_course2");
		return model;
	}
	
	@RequestMapping(value = "/addCourse2",method = RequestMethod.POST)
    public @ResponseBody String addCourse (@RequestBody Training training){
       return trainingService.saveTraining(training);
    }

}
