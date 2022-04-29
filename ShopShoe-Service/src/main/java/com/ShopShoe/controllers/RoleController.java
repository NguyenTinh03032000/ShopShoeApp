package com.ShopShoe.controllers;

import java.util.List;

import com.ShopShoe.dto.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.dto.RoleDTO;
import com.ShopShoe.service.RoleService;

@RestController
@RequestMapping("role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping()
	public List<RoleDTO> getAllRole() {
		return (List<RoleDTO>) roleService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable(value = "id") Integer id) {
		RoleDTO roleDTO = roleService.getById(id);
		if(roleDTO == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponseDto("Role not found"));
		}else {
			return ResponseEntity.ok(roleDTO);
		}
	}
}
