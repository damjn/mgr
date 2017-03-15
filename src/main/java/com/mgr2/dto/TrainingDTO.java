package com.mgr2.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.hibernate.validator.constraints.NotEmpty;

public class TrainingDTO {

	private int owner_id;
	@NotEmpty(message = "*Please provide course name")
	private String name;
	@NotEmpty(message = "*Please provide course description")
	private String description;
	private ArrayList<ContentDTO> contentList;
	private BigDecimal price;
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
	public ArrayList<ContentDTO> getContentList() {
		if(contentList == null){
			contentList = new ArrayList<ContentDTO>();
		}
		return contentList;
	}
	public void setContentList(ArrayList<ContentDTO> contentList) {
		this.contentList = contentList;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
