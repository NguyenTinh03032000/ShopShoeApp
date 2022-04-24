package com.ShopShoe.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.*;

@Data
@Entity
@Table(name = "user_token")
public class TokenEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name = "id_user")
	private Long id_user;
	
	private String token;
	
	private Date time_expired;
	
	private Date create_date;
	
	private Date update_date;

	public TokenEntity(Long id_user, String token, Date time_expired, Date createDate, Date updateDate) {
		super();
		this.id_user = id_user;
		this.token = token;
		this.time_expired = time_expired;
		this.create_date = createDate;
		this.update_date = updateDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTime_expired(Date time_expired) {
		this.time_expired = time_expired;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Long getId() {
		return id;
	}

	public Long getId_user() {
		return id_user;
	}

	public String getToken() {
		return token;
	}

	public Date getTime_expired() {
		return time_expired;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public TokenEntity(Long id, Long id_user, String token, Date time_expired, Date create_date, Date update_date) {
		this.id = id;
		this.id_user = id_user;
		this.token = token;
		this.time_expired = time_expired;
		this.create_date = create_date;
		this.update_date = update_date;
	}

	public TokenEntity() {
	}
}
