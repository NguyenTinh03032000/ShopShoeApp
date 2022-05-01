package com.ShopShoe.dto;

import com.ShopShoe.entity.CategoryEntity;

import lombok.Data;

@Data
public class ProductDTO {

	private long id;

	private String name;
	private double price;
	private String description;

	private String image;

	private CategoryEntity category;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ProductDTO(long id, String name, double price, String description, String image, CategoryEntity category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.category = category;
	}

	public ProductDTO() {
	}
}
