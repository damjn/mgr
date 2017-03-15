package com.mgr2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr2.model.Content;
import com.mgr2.repository.ContentRepository;
import com.mgr2.repository.TrainingRepository;

@Service("contentService")
public class ContentServiceImpl implements ContentService{

	@Autowired
	ContentRepository contentRepository;

	@Override
	public void saveContent(Content content) {
		contentRepository.save(content);
	}
	
}
