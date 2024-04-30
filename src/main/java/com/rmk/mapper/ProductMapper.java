package com.rmk.mapper;

import com.rmk.dto.ProductDto;
import com.rmk.entity.Product;

public class ProductMapper {
	
	// to map Product class with ProductDto
	public static ProductDto mapToProductDto(Product product) {
		return new ProductDto(
				product.getProductId(),
				product.getProductName(),
				product.getProductPrice()
		);
	}
	
	// to map ProductDto class with Product
	public static Product mapToProduct(ProductDto productDto) {
		return new Product(
				productDto.getProductId(),
				productDto.getProductName(),
				productDto.getProductPrice()
		);
	}

}
