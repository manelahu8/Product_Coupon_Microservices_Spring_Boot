package com.zensar.payload;

import lombok.Data;

@Data
public class CouponDto {
	private int couponId;
	private String couponCode;
	private Double discount;
	private String expDate;
}
