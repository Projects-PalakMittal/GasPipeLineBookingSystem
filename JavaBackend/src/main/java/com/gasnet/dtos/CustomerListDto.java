package com.gasnet.dtos;

import com.gasnet.pojo.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerListDto {

	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private Role userRole;
	private AddressDto address;
}
