package com.ShopShoe.dto;

import javax.validation.constraints.NotEmpty;

import com.ShopShoe.common.ERole;

public class RoleDTO {

	private Integer id;

	public RoleDTO(Integer id, ERole name) {
		this.id = id;
		this.name = name;
	}

	public RoleDTO() {
	}

	@NotEmpty(message = "Name cannot be empty")
	private ERole name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
