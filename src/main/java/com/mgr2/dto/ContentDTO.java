package com.mgr2.dto;

import org.springframework.web.multipart.MultipartFile;

public class ContentDTO {
	
	private int id;
	private String path;
	private int order_nr;
	private int training_id;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getOrder_nr() {
		return order_nr;
	}
	public void setOrder_nr(int order_nr) {
		this.order_nr = order_nr;
	}
	public int getTraining_id() {
		return training_id;
	}
	public void setTraining_id(int training_id) {
		this.training_id = training_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
