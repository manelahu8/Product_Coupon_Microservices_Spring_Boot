package com.zensar.payload;

import lombok.Data;

@Data
public class ProductDto {
	private int productId;
	private String productName;
	private String discription;
	private Double price;
	private String couponCode;
}
