package com.zensar.services;

import java.util.List;

import com.zensar.payload.ProductDto;

public interface ProductService {

	List<ProductDto> getAllProducts();

	ProductDto insertProduct(ProductDto productDto);

	ProductDto getProduct(int productId);

	ProductDto updateProduct(ProductDto productDto, int productId);

	void deleteProduct(int productId);

}
