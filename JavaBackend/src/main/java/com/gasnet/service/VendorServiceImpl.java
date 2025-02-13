package com.gasnet.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gasnet.dao.UserDao;
import com.gasnet.dtos.ApiResponse;
import com.gasnet.dtos.NoSuchResourceFound;
import com.gasnet.dtos.UpdateVendorDto;
import com.gasnet.dtos.UserDto;
import com.gasnet.pojo.Address;
import com.gasnet.pojo.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public ApiResponse updateVendorDetails(Long vendorId, UpdateVendorDto updateVendorDto) {
		User vendor = userDao.findById(vendorId).orElseThrow(
				()-> new NoSuchResourceFound("Vendor Dosen't Exists..."));
		vendor.setUserName(updateVendorDto.getUserName());
		vendor.setFirstName(updateVendorDto.getFirstName());
		vendor.setLastName(updateVendorDto.getLastName());
		vendor.setPassword(passwordEncoder.encode(updateVendorDto.getPassword()));
		vendor.setPhoneNo(updateVendorDto.getPhoneNo());
		Address address = modelMapper.map(updateVendorDto.getAddress(),Address.class);
		vendor.setAddress(address);
		return new ApiResponse("Vendor Detail Updated Successfully...");
	}
	
	@Override
	public UserDto fetchVendorDetails(Long vendorId) {
		User vendor = userDao.findById(vendorId).orElseThrow(
				()-> new NoSuchResourceFound("Vendor dosen't Exists"));
		UserDto userDto = modelMapper.map(vendor, UserDto.class);
		return userDto;
	}

}
