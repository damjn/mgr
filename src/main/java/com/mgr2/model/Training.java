package com.mgr2.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "training")
public class Training {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="training_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User user;
	
	@Column(name="name", unique=true)
	private String name;
	
	@Column(name="description")
	private String descritpion;
	
	@OneToMany(mappedBy="training")
	private Set<Content> content;

	@Digits(integer=5, fraction=2)
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "accepted")
	private int accepted;

	public Set<Content> getContent() {
		return content;
	}
	
	public void setContent(Set<Content> content) {
		this.content = content;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescritpion() {
		return descritpion;
	}

	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}
	
}