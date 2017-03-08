package com.mgr2.dto;

import java.io.InputStream;
import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class TrainingDTO {

	private int owner_id;
	@NotEmpty(message = "*Please provide course name")
	private String name;
	@NotEmpty(message = "*Please provide course description")
	private String description;
	private MultipartFile file;
	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
