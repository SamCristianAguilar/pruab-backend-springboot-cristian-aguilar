package com.cristianMasterDigital.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristianMasterDigital.app.entity.Order;
import com.cristianMasterDigital.app.entity.User;
import com.cristianMasterDigital.app.repository.OrderRepository;
import com.cristianMasterDigital.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional()
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional()
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Order> findOrderById(Long id) {
		return orderRepository.findById(id);
	}

	@Override 
	@Transactional
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	@Transactional
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

}
