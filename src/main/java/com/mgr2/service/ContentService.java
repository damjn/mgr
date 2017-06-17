package com.mgr2.service;

import com.mgr2.dto.ContentDTO;

public interface ContentService {
	public String saveContent(ContentDTO content);

	public String deleteContent(int contentId);
}
