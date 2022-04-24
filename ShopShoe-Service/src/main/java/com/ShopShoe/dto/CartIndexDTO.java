package com.ShopShoe.dto;

import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data

public class CartIndexDTO {

	private long id;
	
	private int amount;
	
	@JsonIgnore
	private CartEntity cart;
	
	private ProductEntity product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public CartIndexDTO(long id, int amount, CartEntity cart, ProductEntity product) {
		this.id = id;
		this.amount = amount;
		this.cart = cart;
		this.product = product;
	}

	public CartIndexDTO() {
	}
}
