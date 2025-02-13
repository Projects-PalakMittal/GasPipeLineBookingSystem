package com.gasnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasnet.dtos.ApiResponse;
import com.gasnet.dtos.AuthRequest;
import com.gasnet.dtos.AuthResp;
import com.gasnet.dtos.UserDto;
import com.gasnet.security.JwtUtils;
import com.gasnet.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> addUser(@RequestBody UserDto userDto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(userService.registerNewUser(userDto));
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping("/sign-in")
	@Operation(description = "User sign in")
	public ResponseEntity<?> userSignIn(@RequestBody @Valid
			AuthRequest dto) {
		System.out.println("in sign in "+dto);
		//1. Create auth token using suser supplied em n pwd
		UsernamePasswordAuthenticationToken 
		authenticationToken = new UsernamePasswordAuthenticationToken
		(dto.getEmail(),dto.getPassword());
		System.out.println(authenticationToken.isAuthenticated());//f
		//2. invoke Spring sec supplied auth mgr's authenticate method
		Authentication authToken = 
				authenticationManager.authenticate(authenticationToken);
		//=> auth success
		System.out.println(authToken);
		System.out.println(authToken.isAuthenticated());//t
		//3 . Send auth respone to the client containing JWTS
		return ResponseEntity.status(HttpStatus.OK)
				.body(new AuthResp(userService.login(dto.getEmail(), dto.getPassword()),
						jwtUtils.generateJwtToken(authToken)));		
		
	}
}
