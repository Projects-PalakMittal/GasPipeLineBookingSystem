package com.gasnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasnet.dtos.ApiResponse;
import com.gasnet.dtos.SubscriptionDto;
import com.gasnet.dtos.UpdateVendorDto;
import com.gasnet.service.SubscriptionService;
import com.gasnet.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	@Autowired
	private VendorService vendorService;
	
	@GetMapping("/list-of-customer/{vendorId}")
	public ResponseEntity<?> listOfCustomer(@PathVariable Long vendorId){
		try {
			return ResponseEntity.ok(subscriptionService.listOfCustomer(vendorId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error list of customer..."));
		}
	}
	
	@GetMapping("/list-of-subscription/{vendorId}")
	public ResponseEntity<?> listOfSubscription(@PathVariable Long vendorId){
		try {
			return ResponseEntity.ok(subscriptionService.listOfSubscription(vendorId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error in list of subscription..."));
		}
	}
	
	@PostMapping("/create-new-subscription/{vendorId}")
	public ResponseEntity<?> createNewSubscription(@PathVariable Long vendorId,@RequestBody SubscriptionDto subscriptionDto){
		try {
			return ResponseEntity.ok(subscriptionService.createNewSubscription(vendorId,subscriptionDto));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error in creation of subscription..."));
		}
	}
	
	@PutMapping("/update-vendor-details/{vendorId}")
	public ResponseEntity<?> updateVendorDetails(@PathVariable Long vendorId,@RequestBody UpdateVendorDto updateVendorDto){
		try {
			return ResponseEntity.ok(vendorService.updateVendorDetails(vendorId,updateVendorDto));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error in update vendor details"));
		}
	}
	
	@GetMapping("/fetch-vendor-details/{vendorId}")
	public ResponseEntity<?> fetchVendorDetails(@PathVariable Long vendorId){
		try {
			return ResponseEntity.ok(vendorService.fetchVendorDetails(vendorId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error in fetch vendor details"));
		}
	}
}
