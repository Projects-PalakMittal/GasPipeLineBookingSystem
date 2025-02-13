package com.gasnet.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User{
	
	private List<Transaction> transactions;
	private List<Subscription> subscriptions;
}
