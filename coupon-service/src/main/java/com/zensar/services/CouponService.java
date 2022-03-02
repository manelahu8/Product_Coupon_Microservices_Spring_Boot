package com.zensar.services;

import java.util.List;

import com.zensar.payload.CouponDto;

public interface CouponService {

	public List<CouponDto> getAllCoupon();

	public CouponDto createCoupon(CouponDto couponDto);

	public CouponDto getCoupon(String couponCode);

	public CouponDto updateCoupon(CouponDto couponDto, String couponCode);

	public Integer deleteCoupon(String couponCode);

}
