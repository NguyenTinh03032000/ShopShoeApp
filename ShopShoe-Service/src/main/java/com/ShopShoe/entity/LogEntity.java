package com.ShopShoe.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "log")
public class LogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name_action;
	private String name_method;
	private String content;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="id_product")
	private ProductEntity product;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
	private Date actionDate;

	public LogEntity() {
	}

	public LogEntity(long id, String name_action, String name_method, String content, UserEntity user, ProductEntity product, Date actionDate) {
		this.id = id;
		this.name_action = name_action;
		this.name_method = name_method;
		this.content = content;
		this.user = user;
		this.product = product;
		this.actionDate = actionDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName_action() {
		return name_action;
	}

	public void setName_action(String name_action) {
		this.name_action = name_action;
	}

	public String getName_method() {
		return name_method;
	}

	public void setName_method(String name_method) {
		this.name_method = name_method;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Date getAction_Date() {
		return actionDate;
	}

	public void setAction_Date(Date actionDate) {
		this.actionDate = actionDate;
	}
}
