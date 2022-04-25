package com.File.dto;
public class MessageResponseDto {
	private String message;

	public MessageResponseDto(String message) {
		this.message = message;
	}

	public MessageResponseDto() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
