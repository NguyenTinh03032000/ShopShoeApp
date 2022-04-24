package com.ShopShoe.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "discount")
public class DiscountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name_discount;
	
	private Date date_discount;
	
	private int percent_discount;

	@ManyToOne
	@JoinColumn(name="id_product")
	private ProductEntity product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName_discount() {
		return name_discount;
	}

	public void setName_discount(String name_discount) {
		this.name_discount = name_discount;
	}

	public Date getDate_discount() {
		return date_discount;
	}

	public void setDate_discount(Date date_discount) {
		this.date_discount = date_discount;
	}

	public int getPercent_discount() {
		return percent_discount;
	}

	public void setPercent_discount(int percent_discount) {
		this.percent_discount = percent_discount;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public DiscountEntity(long id, String name_discount, Date date_discount, int percent_discount, ProductEntity product) {
		this.id = id;
		this.name_discount = name_discount;
		this.date_discount = date_discount;
		this.percent_discount = percent_discount;
		this.product = product;
	}

	public DiscountEntity() {
	}
}
