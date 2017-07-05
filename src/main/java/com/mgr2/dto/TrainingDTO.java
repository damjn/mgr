package com.mgr2.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mgr2.model.Category;
import com.mgr2.model.Content;


public class TrainingDTO {

	private int id;
	private UserDTO userDTO;
	private String name;
	private String description;
	private List<ContentDTO> contentList;
	private BigDecimal price;
	private int points_price;
	// private int accepted; // dodac lub nie
	private Category category;
	private BigDecimal averageRate;
	
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
	public List<ContentDTO> getContentList() {
		if(contentList == null){
			contentList = new ArrayList<ContentDTO>();
		}
		return contentList;
	}
	public void setContentList(List<ContentDTO> set) {
		this.contentList = set;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getPoints_price() {
		return points_price;
	}
	public void setPoints_price(int points_price) {
		this.points_price = points_price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public BigDecimal getAverageRate() {
		return averageRate;
	}
	public void setAverageRate(BigDecimal averageRate) {
		this.averageRate = averageRate;
	}
	
	
}
