package com.mgr2.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.mgr2.model.Content;


public class TrainingDTO {

	private int id;
	private UserDTO userDTO;
	private String name;
	private String description;
	private Set<ContentDTO> contentList;
	private BigDecimal price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
	public Set<ContentDTO> getContentList() {
		if(contentList == null){
			contentList = new HashSet<ContentDTO>();
		}
		return contentList;
	}
	public void setContentList(Set<ContentDTO> set) {
		this.contentList = set;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
