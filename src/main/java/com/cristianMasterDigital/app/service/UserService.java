package com.cristianMasterDigital.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import com.cristianMasterDigital.app.entity.Order;
import com.cristianMasterDigital.app.entity.User;


public interface UserService {

	public Iterable<User> findAll();
	
	public Page<User> findAll(Pageable pageable);
	
	public Optional<User> findById(Long id);
	
	public User save(User user);
	
	public void deleteById(Long id);
	
	public Optional<Order> findOrderById(Long id);
	
	public Order saveOrder(Order order);
	
	public void deleteOrder(Long Id);
	
}
