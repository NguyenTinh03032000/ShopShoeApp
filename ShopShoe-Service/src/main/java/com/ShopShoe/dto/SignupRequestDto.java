package com.ShopShoe.dto;

import org.hibernate.validator.constraints.Length;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class SignupRequestDto {
	private String name;
	
	private String address;
	
	private String phone_number;
	
	@NotEmpty(message = "Invalid username")
	private String username;
	
	@Email(message = "Invalid email")
	private String email;
	
	@NotEmpty(message = "Missing password")
	@Length(min=8, max=32, message="Password must be 8-32 characters long")
	private String password;

	private long scores;
	
	private Set<String> role;

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

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public SignupRequestDto() {
	}

	public SignupRequestDto(String name, String address, String phone_number, String username, String email, String password, long scores, Set<String> role) {
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
		this.username = username;
		this.email = email;
		this.password = password;
		this.scores = scores;
		this.role = role;
	}
}
