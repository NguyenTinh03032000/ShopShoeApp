package com.ShopShoe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {
	
	private Long id;
	
	private String name;
	
	private String address;
	
	private String phone_number;

	@NotEmpty(message = "Username cannot be empty")
	private String username;

	@NotEmpty(message = "Email cannot be empty")
	@Email
	private String email;

	@Length(min=8, max=32, message="Password must be 8-32 characters long")
	private String password;
	
	private long scores;
	
//	@JsonProperty("roles")
//	private Set<RoleDTO> roles = new HashSet<>();

	public UserDTO(String name,String address,String phone_number,String username, String email, String password,long scores) {
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
		this.username = username;
		this.email = email;
		this.password = password;
		this.scores = scores;
	}
	public UserDTO(){}

	public UserDTO(Long id, String name, String address, String phone_number, String username, String email, String password, long scores) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
		this.username = username;
		this.email = email;
		this.password = password;
		this.scores = scores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getScores() {
		return scores;
	}

	public void setScores(long scores) {
		this.scores = scores;
	}
}
