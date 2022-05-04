package com.ShopShoe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private double price;
	private String description;
	private String image;

	@ManyToOne
	@JoinColumn(name = "id_category")
	private CategoryEntity category;

	private Date create_Date;

	private Date update_Date;

	public Date getCreateDate() {
		return create_Date;
	}

	public void setCreateDate(Date createDate) {
		this.create_Date = createDate;
	}

	public Date getUpdateDate() {
		return update_Date;
	}

	public void setUpdateDate(Date updateDate) {
		this.update_Date = updateDate;
	}

	public ProductEntity(long id, String name, double price, String description, String image, CategoryEntity category, Date createDate, Date updateDate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.category = category;
		this.create_Date = createDate;
		this.update_Date = updateDate;
	}

	public ProductEntity() {
	}

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
}
