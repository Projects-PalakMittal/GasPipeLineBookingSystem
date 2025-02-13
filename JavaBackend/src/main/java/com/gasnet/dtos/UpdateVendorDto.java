package com.gasnet.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVendorDto {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String password;
	private AddressDto address;
}
