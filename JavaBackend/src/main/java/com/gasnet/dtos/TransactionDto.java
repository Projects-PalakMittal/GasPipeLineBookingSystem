package com.gasnet.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {
	
//	private UserDto customer;
	private SubscriptionDto subscriptionDto;
	private Double buyPrice;
	private Integer quantity;
}
