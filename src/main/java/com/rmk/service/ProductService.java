package com.rmk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmk.dto.ProductDto;

@Service
public interface ProductService {

	public ProductDto createNewProduct(ProductDto productDto); 
	
	public ProductDto getProductById(long productId);
	
	public List<ProductDto> getAllProducts();
	
	public ProductDto updateProduct(ProductDto productDto, long productId);
	
	public void deleteProduct(long productId);
};
