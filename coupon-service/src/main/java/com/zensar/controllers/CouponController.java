package com.zensar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zensar.payload.CouponDto;
import com.zensar.services.CouponService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/coupons")
@Tag(name = "Coupon Resource")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@GetMapping("/")
	public ResponseEntity<List<CouponDto>> getAllCoupons() {
		return new ResponseEntity<>(couponService.getAllCoupon(), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponDto couponDto) {
		return new ResponseEntity<>(couponService.createCoupon(couponDto), HttpStatus.CREATED);
	}

	@GetMapping("/{couponCode}")
	@Operation(summary = "Get Coupon by coupon code",description = "Get Coupon by coupon code i.e pass coupouncode in url")
	public ResponseEntity<CouponDto> getCoupon(@PathVariable("couponCode") String couponCode) {
		return new ResponseEntity<>(couponService.getCoupon(couponCode), HttpStatus.OK);
	}

	@PutMapping("/{couponCode}")
	public ResponseEntity<CouponDto> updateCoupon(@PathVariable("couponCode") String couponCode,
			@RequestBody CouponDto couponDto) {
		return new ResponseEntity<>(couponService.updateCoupon(couponDto, couponCode), HttpStatus.CREATED);
	}

	@DeleteMapping("/{couponCode}")
	public ResponseEntity<String> deleteCoupon(@PathVariable("couponCode") String couponCode) {
		Integer count = couponService.deleteCoupon(couponCode);
		if (count > 0)
			return new ResponseEntity<>("Coupon deleted successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("Coupon deletion failure", HttpStatus.NOT_FOUND);
	}
}
