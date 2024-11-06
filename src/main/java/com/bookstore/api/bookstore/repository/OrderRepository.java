package com.bookstore.api.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.api.bookstore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	

}
