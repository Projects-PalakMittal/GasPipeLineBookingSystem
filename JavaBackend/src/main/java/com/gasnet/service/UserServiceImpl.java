package com.gasnet.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gasnet.dtos.ApiException;
import com.gasnet.dao.UserDao;
import com.gasnet.dtos.ApiResponse;
import com.gasnet.dtos.NoSuchResourceFound;
import com.gasnet.dtos.ServiceResp;
import com.gasnet.dtos.UserDto;
import com.gasnet.pojo.Status;
import com.gasnet.pojo.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ServiceResp login(String Email,String Password) {
		// TODO Auto-generated method stub
		System.out.println("in login");
		User userFound = userDao.findByEmailAndUserStatus(Email, Status.ACTIVE).orElseThrow(
				() -> new NoSuchResourceFound("Invalid Email..."));
		if(passwordEncoder.matches(Password,userFound.getPassword())) {
			UserDto userDto = modelMapper.map(userFound, UserDto.class);
			userDto.setPassword(Password);
			System.out.println(userDto);
			return new ServiceResp(userDto,new ApiResponse("SuccessFull Login..."));
		}
		else {
			return new ServiceResp(new ApiResponse("Invalid Password..."));
		}
	}
	
	@Override
	public ApiResponse registerNewUser(UserDto dto) {
		// chk if user alrdy exists
		if (userDao.existsByEmail(dto.getEmail()))
			throw new ApiException("User email already exists!!!!");
		// map dto -> entity
		User user = modelMapper.map(dto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserStatus(Status.ACTIVE);
		User savedUser = userDao.save(user);
		return new ApiResponse("User registered with ID " + savedUser.getId());
	}

}
