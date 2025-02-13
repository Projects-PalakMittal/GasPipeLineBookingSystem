package com.gasnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "balance")
public class GasBalance extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "customer_id",nullable = false)
	private User customer;
	@Column(name = "balance_amount",nullable = false)
	private Double balanceAmount;
}
