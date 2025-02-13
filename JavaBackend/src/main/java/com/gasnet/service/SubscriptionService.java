package com.gasnet.service;

import java.util.List;

import com.gasnet.dtos.ApiResponse;
import com.gasnet.dtos.CustomerListDto;
import com.gasnet.dtos.SubscriptionDto;

public interface SubscriptionService {

	List<CustomerListDto> listOfCustomer(Long vendorId);

	ApiResponse createNewSubscription(Long vendorId, SubscriptionDto subscriptionDto);

	List<SubscriptionDto> listOfSubscription(Long vendorId);

}
