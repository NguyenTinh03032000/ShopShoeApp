package com.ShopShoe.dto;

import com.ShopShoe.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CartDTO {
	
	private long id;
	
	private Double total_money;
	
	private UserEntity user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getTotal_money() {
		return total_money;
	}

	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CartDTO(long id, Double total_money, UserEntity user) {
		this.id = id;
		this.total_money = total_money;
		this.user = user;
	}

	public CartDTO() {
	}
}
