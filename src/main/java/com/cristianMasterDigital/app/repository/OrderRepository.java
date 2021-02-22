package com.cristianMasterDigital.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianMasterDigital.app.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
