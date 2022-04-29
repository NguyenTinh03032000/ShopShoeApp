package com.ShopShoe.controllers;

import java.util.List;

import com.ShopShoe.dto.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ShopShoe.dto.CategoryDTO;
import com.ShopShoe.entity.CategoryEntity;
import com.ShopShoe.service.CategoryService;


@RestController
@RequestMapping("category")
@PreAuthorize("hasRole('ADMIN') or hasRole('SALESMAN')")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	public List<CategoryDTO> getAllCategory() {
		return (List<CategoryDTO>) categoryService.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable long id){
		try {
			CategoryEntity category = categoryService.getById(id);
			if(category == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponseDto("Catalog not found"));
			}else{
				return ResponseEntity.ok(category);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponseDto("Error"));
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> createCategory(@Validated @RequestBody CategoryEntity category) {
		try {
			if(categoryService.findExactlyName(category.getName()) != null){
				return ResponseEntity.ok(new MessageResponseDto("Category name already exists"));
			}else {
				CategoryEntity categoryEntity = categoryService.save(category);
				return ResponseEntity.ok(categoryEntity);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponseDto("Error"));
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable long id){
		try {
			CategoryEntity category = categoryService.getById(id);
			if(category == null){
				return "Catalog not found";
			}else{
				categoryService.delete(category);
				return "Delete successful";
			}
		} catch (Exception e) {
			return "Error";
		}	
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable long id,@Validated @RequestBody CategoryEntity newCategory){
		try {
			CategoryEntity category = categoryService.getById(id);
			if(category == null){
				return ResponseEntity.ok(new MessageResponseDto("Catalog not found"));
			}else{
				if(categoryService.findExactlyName(newCategory.getName()) != null){
					return ResponseEntity.ok(new MessageResponseDto("Category name already exists"));
				}else{
					category.setName(newCategory.getName());
					categoryService.save(category);
					return ResponseEntity.ok(new MessageResponseDto("Update successful"));
				}
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponseDto("Error"));
		}
	}
}
