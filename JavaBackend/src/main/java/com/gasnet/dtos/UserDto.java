package com.gasnet.dtos;

import java.time.LocalDate;

import com.gasnet.pojo.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	
	private String userName;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String phoneNo;
	private String email;
	private String password;
	private String aadharCard;
	private String panCard;
	private Role userRole;
	private AddressDto address;
}
