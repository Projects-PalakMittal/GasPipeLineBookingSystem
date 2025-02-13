package com.gasnet.service;

import com.gasnet.dtos.ApiResponse;
import com.gasnet.dtos.ServiceResp;
import com.gasnet.dtos.UserDto;

public interface UserService {

	ServiceResp login(String Email,String Password);

	ApiResponse registerNewUser(UserDto dto);

}
