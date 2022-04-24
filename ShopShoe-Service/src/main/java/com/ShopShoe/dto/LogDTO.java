package com.ShopShoe.dto;

import java.util.*;

import com.ShopShoe.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LogDTO {

	private long id;
	private String name_method;
	private String content;
	private long idProduct;
	private long idUser;
	private String  nameUser;
	@JsonIgnore
	private UserEntity user;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+7")
	private Date  action_Date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Date getAction_Date() {
		return action_Date;
	}

	public void setAction_Date(Date action_Date) {
		this.action_Date = action_Date;
	}

	public LogDTO() {
	}

	public LogDTO(long id, String name_method, String content, long idProduct, long idUser, String nameUser, UserEntity user, Date action_Date) {
		this.id = id;
		this.name_method = name_method;
		this.content = content;
		this.idProduct = idProduct;
		this.idUser = idUser;
		this.nameUser = nameUser;
		this.user = user;
		this.action_Date = action_Date;
	}
}
