package com.gasnet.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResp {
	
	private UserDto userDto;
	private ApiResponse apiResponse;
	
	public ServiceResp(UserDto userDto){
		this.userDto = userDto;
	}
	public ServiceResp(ApiResponse apiResponse){
		this.apiResponse = apiResponse;
	}
}
