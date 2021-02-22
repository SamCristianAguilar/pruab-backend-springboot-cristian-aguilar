package com.cristianMasterDigital.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cristianMasterDigital.app.entity.Product;



public interface ProductService {
	
public Iterable<Product> findAll();
	
	public Page<Product> findAll(Pageable pageable);
	
	public Optional<Product> findById(Long id);
	
	public Product save(Product product);
	

}
