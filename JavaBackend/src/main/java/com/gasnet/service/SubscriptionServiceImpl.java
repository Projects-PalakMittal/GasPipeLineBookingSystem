package com.gasnet.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasnet.dao.SubscriptionDao;
import com.gasnet.dao.TransactionDao;
import com.gasnet.dao.UserDao;
import com.gasnet.dtos.ApiResponse;
import com.gasnet.dtos.CustomerListDto;
import com.gasnet.dtos.NoSuchResourceFound;
import com.gasnet.dtos.SubscriptionDto;
import com.gasnet.pojo.Subscription;
import com.gasnet.pojo.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SubscriptionDao subscriptionDao;
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
	private UserDao userDao; 

	@Override
	public List<CustomerListDto> listOfCustomer(Long vendorId) {
		User vendor = userDao.findById(vendorId).orElseThrow(()-> new NoSuchResourceFound("No Such vendor exists..."));
		List<Subscription> subscription = subscriptionDao.findByVendor(vendor);
		List<CustomerListDto> customerListDtos = new ArrayList<>();
		for (Subscription sub : subscription) {
			customerListDtos.add(modelMapper.map(transactionDao.findBySubscription(sub), CustomerListDto.class));
		}
		return customerListDtos;
	}

	@Override
	public ApiResponse createNewSubscription(Long vendorId, SubscriptionDto subscriptionDto) {
		User vendor = userDao.findById(vendorId).orElseThrow(()-> new NoSuchResourceFound("No Such vendor exists..."));
		Subscription subscription = modelMapper.map(subscriptionDto, Subscription.class);
		subscription.setVendor(vendor);
		subscriptionDao.save(subscription);
		return new ApiResponse("Subscription Created Successfully..");
	}

	@Override
	public List<SubscriptionDto> listOfSubscription(Long vendorId) {
		User vendor = userDao.findById(vendorId).orElseThrow(()-> new NoSuchResourceFound("No Such vendor exists..."));
		List<Subscription> subscription = subscriptionDao.findByVendor(vendor);
		List<SubscriptionDto> subscriptionDtos = new ArrayList<>();
		for (Subscription sub : subscription) {
			subscriptionDtos.add(modelMapper.map(sub, SubscriptionDto.class));
		}
		return subscriptionDtos;
	}

}
