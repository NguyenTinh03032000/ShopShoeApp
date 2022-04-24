package com.ShopShoe.service;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T> implements IService<T>{

	@Override
	public void delete(T entity) {};
	
	@Override
	public T save(T entity) {
		return entity;
	};
	
	
}