package com.zensar.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.entity.Coupon;
import com.zensar.exception.ResourceNotFoundException;
import com.zensar.payload.CouponDto;
import com.zensar.repository.CouponRepository;

@Service
@Transactional
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Override
	public List<CouponDto> getAllCoupon() {
		List<Coupon> coupons = couponRepository.findAll();
		return coupons.stream().map((coupon) -> mapToDto(coupon)).collect(Collectors.toList());
	}

	@Override
	public CouponDto createCoupon(CouponDto couponDto) {
		Coupon coupon = couponRepository.save(mapToEntity(couponDto));
		return mapToDto(coupon);
	}

	@Override
	public CouponDto getCoupon(String couponCode) {
		Coupon coupon = couponRepository.findByCouponCode(couponCode);
		if (coupon == null)
			throw new ResourceNotFoundException("Coupon", "Coupon Code", couponCode);
		return mapToDto(coupon);
	}

	@Override
	public CouponDto updateCoupon(CouponDto couponDto, String couponCode) {
		Coupon coupon = couponRepository.findByCouponCode(couponCode);
		if (coupon == null)
			throw new ResourceNotFoundException("Coupon", "Coupon Code", couponCode);
		coupon.setCouponCode(couponDto.getCouponCode());
		coupon.setDiscount(couponDto.getDiscount());
		coupon.setExpDate(couponDto.getExpDate());

		return mapToDto(couponRepository.save(coupon));
	}

	@Override
	public Integer deleteCoupon(String couponCode) {
		return couponRepository.deleteByCouponCode(couponCode);
	}

	private Coupon mapToEntity(CouponDto couponDto) {
		Coupon coupon = new Coupon();
		coupon.setCouponId(couponDto.getCouponId());
		coupon.setCouponCode(couponDto.getCouponCode());
		coupon.setDiscount(couponDto.getDiscount());
		coupon.setExpDate(couponDto.getExpDate());
		return coupon;
	}

	private CouponDto mapToDto(Coupon coupon) {
		CouponDto couponDto = new CouponDto();
		couponDto.setCouponId(coupon.getCouponId());
		couponDto.setCouponCode(coupon.getCouponCode());
		couponDto.setDiscount(coupon.getDiscount());
		couponDto.setExpDate(coupon.getExpDate());
		return couponDto;
	}
}
