package com.mgr2.service.impl;

import java.util.List;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mgr2.dto.ContentDTO;
import com.mgr2.dto.convertion.ContentDTOConverter;
import com.mgr2.model.Content;
import com.mgr2.repository.ContentRepository;
import com.mgr2.repository.TrainingRepository;
import com.mgr2.service.ContentService;
import com.mgr2.storage.StorageService;

@Service("contentService")
public class ContentServiceImpl implements ContentService {

	@Autowired
	ContentRepository contentRepository;

	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	StorageService storageService;
	
	@Autowired
	ContentDTOConverter contentDTOConverter;

	@Override
	public String saveContent(ContentDTO cDTO) {

		try {
			contentRepository.save(contentDTOConverter.convertDTOtoModel(cDTO));
			return "CONTENT_ADDED";
		} catch (PersistentObjectException e) {
			return "ERROR_WHILE_CONTENT_ADDING";
		}
	}

	@Override
	public String deleteContent(int contentId) {
		try {
			String path = contentRepository.findOne(contentId).getPath();
			storageService.delete(path);
			contentRepository.delete(contentId);
			return "CONTENT_DELETED";
		} catch (PersistentObjectException e) {
			return "ERROR_WHILE_CONTENT_DELETING";
		}
	}

	@Override
	public List<ContentDTO> getCourseContent(int courseId) {
		return contentDTOConverter.convertListOfModelsToDTOList(contentRepository.findByTrainingId(courseId));
	}

	@Override
	public MultipartFile getFile(int content_id) {
		String path = contentRepository.findById(content_id).getPath();
		return storageService.loadAsMultipartFile(path);
	}
	
	

	// customerDao.add(CustomerVoConvertion.convertVoToEntity(cVo));
}
