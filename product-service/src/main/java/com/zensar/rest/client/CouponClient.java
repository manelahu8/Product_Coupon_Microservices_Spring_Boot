package com.zensar.rest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.zensar.entity.Coupon;

@FeignClient("GATEWAY-SERVICE")
public interface CouponClient {
	@GetMapping("/coupons/{couponCode}")
	Coupon getCoupon(@PathVariable("couponCode") String couponCode,@RequestHeader("Authorization") String bearerToken);
}
