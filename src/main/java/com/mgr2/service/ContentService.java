package com.mgr2.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mgr2.dto.ContentDTO;

public interface ContentService {
	public String saveContent(ContentDTO content);

	public String deleteContent(int contentId);

	public List<ContentDTO> getCourseContent(int courseId);

	public MultipartFile getFile(int content_id);
}
