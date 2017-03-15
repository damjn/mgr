package com.mgr2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "content")
public class Content {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="content_id")
	private int id;
	
	@Column(name="path")
	private String path;
	
	@Column(name="order_nr")
	private int order_nr;
	
	@ManyToOne
	@JoinColumn(name="training_id")
	private Training training;
	
	@Column(name="description")
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

	public int getOrder() {
		return order_nr;
	}

	public void setOrder(int order) {
		this.order_nr = order;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
