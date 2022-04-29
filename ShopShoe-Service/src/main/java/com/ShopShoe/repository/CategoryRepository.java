package com.ShopShoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ShopShoe.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	List<CategoryEntity> findByName(String name);

	@Query(value = "select * from category where category.name = ?1", nativeQuery = true)
	CategoryEntity findExactlyName(String name);

	@Query(value = "select * from category where category.id = ?1", nativeQuery = true)
	CategoryEntity getById(long id);
}
