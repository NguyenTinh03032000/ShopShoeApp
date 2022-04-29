package com.ShopShoe.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.ShopShoe.common.ERole;
import com.ShopShoe.common.JwtUtils;
import com.ShopShoe.dto.JwtResponseDto;
import com.ShopShoe.dto.LoginRequestDto;
import com.ShopShoe.dto.MessageResponseDto;
import com.ShopShoe.dto.SignupRequestDto;
import com.ShopShoe.entity.RoleEntity;
import com.ShopShoe.entity.TokenEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.service.RoleService;
import com.ShopShoe.service.TokenService;
import com.ShopShoe.service.UserService;
import com.ShopShoe.service.Implements.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("account")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	/**
	 * @Content login
	 * @param loginRequest
	 * @return information account and token login
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequestDto loginRequest){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponseDto(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}
	
	@GetMapping("/logout")
	public String logoutUser(){
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
			UserEntity currentUser = userService.findId(u.getId());

			TokenEntity tokenEntity = tokenService.findByUserId(currentUser.getId());
			tokenEntity.setToken("");
			tokenEntity.setTime_expired(null);
			tokenEntity.setCreate_date(null);
			tokenEntity.setUpdate_date(null);
			tokenService.save(tokenEntity);
			return "Logout successful";
		} catch (Exception e) {
			return "Logout fail";
		}
	}

	/**
	 * @Content signup for admin
	 * @param signupRequest
	 * @return user
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/signupAdmin")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequestDto signupRequest){
		if(userService.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("Error: Username is already taken!"));
		}
		
		if(userService.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("Error: Email is already in use!"));
		}
		
		UserEntity user = new UserEntity(
				signupRequest.getName(),
				signupRequest.getAddress(),
				signupRequest.getPhone_number(),
				signupRequest.getUsername(),
				signupRequest.getEmail(),
				encoder.encode(signupRequest.getPassword()),
				signupRequest.getScores()
				);
		
		Set<String> strRole = signupRequest.getRole();
		Set<RoleEntity> roles = new HashSet<>();
		
		if(strRole == null) {
			RoleEntity customerRole = roleService.findByName(ERole.ROLE_CUSTOMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(customerRole);
		}else {
			strRole.forEach(role ->{
				switch (role) {
				case "admin":
						RoleEntity adminRole = roleService.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
					break;
				
				case "salesman":
					RoleEntity saleRole = roleService.findByName(ERole.ROLE_SALESMAN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(saleRole);
					break;
					
				default:
					RoleEntity customerRole = roleService.findByName(ERole.ROLE_CUSTOMER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(customerRole);
				}
			});
		}
		user.setRoles(roles);
		userService.save(user);
		
		return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
	}
	/**
	 * 
	 * @param signupRequest
	 * @return register successfully
	 */
	@PostMapping("/signupCustomer")
	public ResponseEntity<?> registerUserByCustomer(@Valid @RequestBody SignupRequestDto signupRequest){
		if(userService.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("Error: Username is already taklen!"));

		}

		if(userService.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("Error: Email is already in use!"));
		}

		UserEntity user = new UserEntity(
				signupRequest.getName(),
				signupRequest.getAddress(),
				signupRequest.getPhone_number(),
				signupRequest.getUsername(),
				signupRequest.getEmail(),
				encoder.encode(signupRequest.getPassword()),
				signupRequest.getScores()
		);

		Set<String> strRole = signupRequest.getRole();
		Set<RoleEntity> roles = new HashSet<>();

		if(strRole == null) {
			RoleEntity customerRole = roleService.findByName(ERole.ROLE_CUSTOMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(customerRole);
		}else {
			strRole.forEach(role ->{
				RoleEntity customerRole = roleService.findByName(ERole.ROLE_CUSTOMER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(customerRole);
			});
		}
		user.setRoles(roles);
		userService.save(user);
		return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
	}

	// export error validation
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}

