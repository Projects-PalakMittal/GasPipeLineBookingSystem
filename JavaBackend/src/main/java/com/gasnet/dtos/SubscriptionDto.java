package com.gasnet.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubscriptionDto {
	
	private String subscriptionName;
	private Double unitPrice;
	private Integer minimumQuantity;
//    private UserDto vendor;
}
