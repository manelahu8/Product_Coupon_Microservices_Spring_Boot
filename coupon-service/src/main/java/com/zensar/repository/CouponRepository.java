package com.zensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findByCouponCode(String couponCode);
	
	@Modifying
	@Query(value = "DELETE FROM Coupon f where f.couponCode = ?1")
	int deleteByCouponCode(String couponCode);
}
