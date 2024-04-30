package com.rmk.controller;

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

import com.rmk.dto.ProductDto;
import com.rmk.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/rmk")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;

	public ProductServiceImpl getProductServiceImpl() {
		return productServiceImpl;
	}

	public void setProductServiceImpl(ProductServiceImpl productServiceImpl) {
		this.productServiceImpl = productServiceImpl;
	}

	public ProductController(ProductServiceImpl productServiceImpl) {
		super();
		this.productServiceImpl = productServiceImpl;
	}

	public ProductController() {
		super();
	}
	
	@PostMapping("create-new-product")
	public ResponseEntity<ProductDto> createNewProduct(@RequestBody ProductDto productDto){
		//return new ResponseEntity<ProductDto>(productServiceImpl.createNewProduct(productDto), HttpStatus.CREATED);
		
		ProductDto productDto2 = productServiceImpl.createNewProduct(productDto);
		return new ResponseEntity<ProductDto>(productDto2,HttpStatus.CREATED);
	}
	
	@GetMapping("{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") long productId){
		//return new ResponseEntity<ProductDto>(productServiceImpl.getProductById(productId), HttpStatus.OK);
		
		ProductDto productDto = productServiceImpl.getProductById(productId);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}
	
	@GetMapping("/get-all-products")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> products = productServiceImpl.getAllProducts();
		return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
	}
	
	@PutMapping("{productId}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable("productId")long productId){
		ProductDto productDtoUpdated = productServiceImpl.updateProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(productDtoUpdated, HttpStatus.OK);	
	}
	
	@DeleteMapping("{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId")long productId){
		productServiceImpl.deleteProduct(productId);
		return new ResponseEntity<String>("Product Deleted Successfully...", HttpStatus.OK);
	}
}
