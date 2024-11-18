package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	public List<Order> findAllByStatus(Integer status);
	
	public List<Order> findAllByUser_UserId(Integer userId);

}
