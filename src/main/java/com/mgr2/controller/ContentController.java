package com.mgr2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mgr2.dto.ContentDTO;
import com.mgr2.model.Training;
import com.mgr2.repository.ContentRepository;
import com.mgr2.repository.TrainingRepository;
import com.mgr2.service.ContentService;
import com.mgr2.storage.StorageService;

@Controller
public class ContentController {

	@Autowired
	ContentService contentService;

	@Autowired
	StorageService storageService;

	@Autowired
	ContentRepository contentRepository;

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
	public @ResponseBody String addFile(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String trainingName) {
		System.out.println("nazwa pliku: " + file.getOriginalFilename());
		System.out.println("nazwa kursu: " + trainingName);
		return storageService.store(file, trainingName);
	}

	@RequestMapping(value = "/content/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteContent(@PathVariable("id") int contentId) {
		contentService.deleteContent(contentId);
		return "CONTENT DELETED";
	}
	
	@RequestMapping(value = "/course_content/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ContentDTO> getCourseContent(@PathVariable("id") int courseId) {
		return contentService.getCourseContent(courseId);
	}
	
	@RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
	public @ResponseBody MultipartFile getFile(@PathVariable("id") int content_id) {
		return contentService.getFile(content_id);
	}
	

}