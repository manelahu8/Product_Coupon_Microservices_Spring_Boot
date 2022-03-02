package com.zensar.controller;

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
import org.springframework.web.client.RestTemplate;
import com.zensar.entity.Coupon;
import com.zensar.payload.ProductDto;
import com.zensar.rest.client.CouponClient;
import com.zensar.services.ProductService;
import com.zensar.utils.JwtUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Resource")
public class ProductController {

	@Autowired
	private ProductService productService;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private CouponClient couponClient;

	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ProductDto> insertProduct(@RequestBody ProductDto productDto) {
		// http://localhost:8081/coupons/{code}

		// if coupon code is not there do exeception handling or validation throw
		// couponNotFoundException

		// Coupon coupon =
		// restTemplate.getForObject("http://COPOUN-SERVICE/coupons/"+product.getCouponCode(),
		// Coupon.class);

		Coupon coupon = couponClient.getCoupon(productDto.getCouponCode(),"Bearer "+UserController.jwtToken);
		
		productDto.setPrice(productDto.getPrice() - coupon.getDiscount());
		return new ResponseEntity<>(productService.insertProduct(productDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{productId}")
	@Operation(summary = "Get Product by product id",description = "Get Product by product id i.e pass product id in url")
	public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") int productId){
		return new ResponseEntity<>(productService.getProduct(productId),HttpStatus.OK);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") int productId,@RequestBody ProductDto productDto){
		return new ResponseEntity<>(productService.updateProduct(productDto, productId),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId){
		return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
	}

}
