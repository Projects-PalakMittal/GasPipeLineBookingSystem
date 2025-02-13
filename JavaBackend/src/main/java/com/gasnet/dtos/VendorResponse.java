package com.gasnet.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendorResponse {
	
	private UserDto userDto;
	private ApiResponse apiResponse;
	private SubscriptionDto subscriptionDto;
}
