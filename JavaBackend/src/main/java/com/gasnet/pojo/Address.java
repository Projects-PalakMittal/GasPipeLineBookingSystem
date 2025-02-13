package com.gasnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Address")
public class Address extends BaseEntity{
	
	@Column(name = "address",length = 100,nullable = false)
	private String Address;
	@Column(name = "city",length = 30,nullable = false)
	private String City;
	@Column(name = "state",length = 30,nullable = false)
	private String State;
	@Column(name = "pincode",nullable = false)
	private Integer PinCode;
	
}
