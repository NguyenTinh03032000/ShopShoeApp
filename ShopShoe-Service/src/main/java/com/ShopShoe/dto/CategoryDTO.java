package com.ShopShoe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CategoryDTO {

	@JsonProperty("id")
	private long id;
	
	@JsonProperty("name")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CategoryDTO() {
	}
}
