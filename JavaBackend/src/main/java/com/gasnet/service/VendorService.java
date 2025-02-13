package com.gasnet.service;

import com.gasnet.dtos.ApiResponse;
import com.gasnet.dtos.UpdateVendorDto;
import com.gasnet.dtos.UserDto;

public interface VendorService {

	ApiResponse updateVendorDetails(Long vendorId, UpdateVendorDto updateVendorDto);

	UserDto fetchVendorDetails(Long vendorId);
	
}
