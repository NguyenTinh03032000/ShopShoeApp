package com.ShopShoe.controllers;

import java.util.*;

import javax.validation.Valid;

import com.ShopShoe.Mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.ShopShoe.dto.MessageResponseDto;
import com.ShopShoe.dto.ProductDTO;
import com.ShopShoe.entity.LogEntity;
import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.service.LogService;
import com.ShopShoe.service.ProductService;
import com.ShopShoe.service.UserService;
import com.ShopShoe.service.Implements.UserDetailsImpl;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("product")
@PreAuthorize("hasRole('ADMIN') or hasRole('SALESMAN')")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private LogService logService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductMapper  productMapper;

	@GetMapping()
	public List<ProductDTO> getAllProduct() {
		return (List<ProductDTO>) productService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable(value = "id") Long id) {
		ProductEntity product = productService.getById(id);
		if(product == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponseDto("No product found"));
		}else{
			return ResponseEntity.ok(productMapper.productEntityToProductDTO(product));
		}
	}
	
	/**
	 * get User Current
	 */
	public UserEntity getUserCurrent() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
		UserEntity currentUser = userService.findId(u.getId());
		return currentUser;
	}

	@PostMapping()
	public ResponseEntity<?> createProduct(@RequestBody ProductEntity product) {
		try {
			if(productService.existsByName(product.getName())){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponseDto("Product already exist"));
			}else {
				Date createDate = new Date((new Date()).getTime());
				product.setCreateDate(createDate);
				productService.save(product);

				LogEntity logEntity = new LogEntity();
				logEntity.setName_action("Add new product");
				logEntity.setName_method("POST");
				logEntity.setContent("Add product: "+product.getName());
				logEntity.setUser(getUserCurrent());
				logEntity.setProduct(product);

				logEntity.setAction_Date(createDate);
				logService.save(logEntity);

				return ResponseEntity.ok(product);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponseDto("Error: "+e));
		}
	}

	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id){
		try {
			ProductEntity product = productService.getById(id);
			if(product == null){
				return "No product found";
			}else{
				productService.delete(product);
				return "Delete product successful";
			}
		} catch (Exception e) {
			return "Error";
		}
	}

	/**
	 * @Content update product
	 * @param id
	 * @param productDetails
	 * @return product
	 */
	@PutMapping(value ="/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Long id,@Valid @RequestBody ProductEntity productDetails) {
		try {
			ProductEntity product = productService.getById(id);
			if(product != null) {
				product.setName(productDetails.getName());
				product.setPrice(productDetails.getPrice());
				product.setDescription(productDetails.getDescription());
				product.setImage(productDetails.getImage());
				product.setCategory(productDetails.getCategory());
				
				LogEntity logEntity = new LogEntity();
				logEntity.setName_action("Update product");
				logEntity.setName_method("PUT");
				logEntity.setContent("Update product: "+product.getName());
				logEntity.setUser(getUserCurrent());
				logEntity.setProduct(product);
				Date Date = new Date((new Date()).getTime());
				logEntity.setAction_Date(Date);
				logService.save(logEntity);
				
				return ResponseEntity.ok(productService.save(product));
			}else {
				return ResponseEntity.ok(new MessageResponseDto("Update object not found"));
			}
		} catch (Exception e) {
		}
		return null;
	}
}
