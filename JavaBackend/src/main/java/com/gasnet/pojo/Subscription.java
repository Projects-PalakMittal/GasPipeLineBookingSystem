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
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subscription")
@ToString
public class Subscription extends BaseEntity {
	
	@Column(name = "subscription_name",length = 50,nullable = false)
	private String subscriptionName;
	@Column(name = "unit_price",nullable = false)
	private Double unitPrice;
	@Column(name = "minimum_quantity",nullable = false)
	private Integer minimumQuantity;
	@ManyToOne
    @JoinColumn(name = "vendor_id")
    private User vendor;
	
}
