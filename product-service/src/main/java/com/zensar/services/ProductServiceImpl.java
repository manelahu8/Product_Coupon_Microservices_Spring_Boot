package com.zensar.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.entity.Product;
import com.zensar.exception.ResourceNotFoundException;
import com.zensar.payload.ProductDto;
import com.zensar.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map((product) -> mapToDto(product)).collect(Collectors.toList());
	}

	@Override
	public ProductDto insertProduct(ProductDto productDto) {
		Product product = mapToEntity(productDto);
		return mapToDto(productRepository.save(product));
	}

	@Override
	public ProductDto getProduct(int productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> {
			throw new ResourceNotFoundException("Product", "Product Id", productId);
		});
		return mapToDto(product);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, int productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> {
			throw new ResourceNotFoundException("Product", "Product Id", productId);
		});
		product.setProductName(productDto.getProductName());
		product.setDiscription(productDto.getDiscription());
		product.setPrice(productDto.getPrice());
		return mapToDto(productRepository.save(product));
	}

	@Override
	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);
		;
	}

	private Product mapToEntity(ProductDto productDto) {
		Product product = new Product();
		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setDiscription(productDto.getDiscription());
		product.setPrice(productDto.getPrice());
		product.setCouponCode(productDto.getCouponCode());
		return product;
	}

	private ProductDto mapToDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(product.getProductId());
		productDto.setProductName(product.getProductName());
		productDto.setDiscription(product.getDiscription());
		productDto.setPrice(product.getPrice());
		productDto.setCouponCode(product.getCouponCode());
		return productDto;
	}
}
