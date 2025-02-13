package com.gasnet.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	private LocalDateTime dateTime;
	private String message;
	
	public ApiResponse(String message) {
		super();
		this.dateTime = LocalDateTime.now();
		this.message = message;
	}
}

