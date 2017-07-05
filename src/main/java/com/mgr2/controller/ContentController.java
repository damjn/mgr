package com.mgr2.controller;

import java.util.List;

import com.mgr2.configuration.MyUserPrincipal;
import com.mgr2.repository.ContentRepository;
import com.mgr2.repository.TrainingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mgr2.dto.ContentDTO;
import com.mgr2.service.ContentService;
import com.mgr2.storage.StorageService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContentController {

	@Autowired
	ContentService contentService;

	@Autowired
	StorageService storageService;

	@Autowired
	ContentRepository contentRepository;
	
	@Autowired
	TrainingRepository trainingRepository;

	@RequestMapping(value = "/content", method = RequestMethod.POST)
	public @ResponseBody String addContent(@RequestBody ContentDTO contentDTO) {
		contentService.saveContent(contentDTO);
		return "SUCCESS";
	}

	@RequestMapping(value = "/content", method = RequestMethod.PUT)
	public @ResponseBody String editContent(@RequestBody ContentDTO contentDTO) {
		contentService.saveContent(contentDTO);
		return "SUCCESS";
	}

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public ModelAndView addFile(@RequestParam("file") MultipartFile file, 
			@RequestParam("description") String description, @RequestParam("orderNr") int orderNr,
			@RequestParam("trainingId") String trainingId) {
		ContentDTO contentDTO = new ContentDTO();
		contentDTO.setDescription(description);
		String trainingName = trainingRepository.findTrainingNameById(Integer.parseInt(trainingId));
		String path = storageService.store(file, trainingName);
		contentDTO.setPath(path);
		contentDTO.setOrder_nr(orderNr);
		contentDTO.setTraining_id(Integer.parseInt(trainingId));
		contentService.saveContent(contentDTO);
		//storageService.store(file, trainingName);
		MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		model.setViewName("loggedIndex");
		return model;

	}

	@RequestMapping(value = "/content/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteContent(@PathVariable("id") int contentId) {
		contentService.deleteContent(contentId);
		return "CONTENT DELETED";
	}

	@RequestMapping(value = "/training_content/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ContentDTO> getTrainingContent(@PathVariable("id") int trainingId) {
		return contentService.getCourseContent(trainingId);
	}

	@RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
	public @ResponseBody MultipartFile getFile(@PathVariable("id") int content_id) {
		return contentService.getFile(content_id);
	}

}
