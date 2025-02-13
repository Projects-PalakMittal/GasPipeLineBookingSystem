package com.gasnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable = false)
	private User customer;
	@ManyToOne
	@JoinColumn(name = "subscription_id",nullable = false)
	private Subscription subscription;
	@Column(name = "buy_price",nullable = false)
	private Double buyPrice;
	@Column(name = "quantity",nullable = false)
	private Integer quantity;
}
