package com.mgr2.dto;

import org.springframework.web.multipart.MultipartFile;

public class ContentDTO {
	private int orderNR;
	private MultipartFile file;
	private String description;
	
	public int getOrderNR() {
		return orderNR;
	}
	public void setOrderNR(int orderNR) {
		this.orderNR = orderNR;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
