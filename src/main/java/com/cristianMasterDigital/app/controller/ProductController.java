package com.cristianMasterDigital.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianMasterDigital.app.entity.Product;
import com.cristianMasterDigital.app.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//Create an product
	@PostMapping("new")
	public ResponseEntity<?> create (@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}
	
	//Read all products
	@GetMapping
	public List<Product> readAll() {
		
		List<Product> products = StreamSupport
				.stream(productService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return products;
	}
	
	
	//Read an product
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long productId){
		Optional<Product> oProduct = productService.findById(productId);
		
		if(!oProduct.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oProduct);
	}
	
	
	

}
