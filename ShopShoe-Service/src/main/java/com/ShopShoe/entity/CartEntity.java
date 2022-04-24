package com.ShopShoe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cart")
public class CartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Double total_money;
	
	@OneToOne
	@JoinColumn(name = "id_user")
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

	public CartEntity(long id, Double total_money, UserEntity user) {
		this.id = id;
		this.total_money = total_money;
		this.user = user;
	}

	public CartEntity() {
	}
}
