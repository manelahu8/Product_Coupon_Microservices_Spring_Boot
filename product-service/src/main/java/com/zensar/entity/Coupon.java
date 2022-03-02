package com.zensar.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coupon {
	private int couponId;
	private String couponCode;
	private Double discount;
	//try to take java.util.Date
	private String expDate;
	
}
