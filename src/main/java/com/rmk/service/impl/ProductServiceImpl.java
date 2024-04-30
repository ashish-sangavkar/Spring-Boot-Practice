package com.rmk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmk.dto.ProductDto;
import com.rmk.entity.Product;
import com.rmk.exception.ResourceNotFoundException;
import com.rmk.mapper.ProductMapper;
import com.rmk.repository.ProductRepository;
import com.rmk.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public ProductServiceImpl() {
		super();
	}

	@Override
	public ProductDto createNewProduct(ProductDto productDto) {
		Product product = ProductMapper.mapToProduct(productDto);
		Product savedProduct = productRepository.save(product);
		return ProductMapper.mapToProductDto(savedProduct);
	}

	@Override
	public ProductDto getProductById(long productId) {
		Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId+" not found"));
		ProductDto productDto = ProductMapper.mapToProductDto(product); 
		return productDto;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		return productList.stream().map((product) -> ProductMapper.mapToProductDto(product)).collect(Collectors.toList());
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, long productId) {
		
		Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId+" product not found"));
		product.setProductName(productDto.getProductName());
		product.setProductPrice(productDto.getProductPrice());
		
		productRepository.save(product);
		Product updatedProduct = productRepository.save(product);
		return ProductMapper.mapToProductDto(product);
		
	}

	@Override
	public void deleteProduct(long productId) {
		Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId+" productId not found"));
		productRepository.deleteById(productId);
	}
	
}
