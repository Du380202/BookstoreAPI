package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.OrderDto;
import com.bookstore.api.bookstore.entity.Order;

public interface OrderService {
	
	public List<Order> findAll();
	
	public List<Order> findAllByStatus(Integer status);
	
	public List<Order> findAllByUserId(Integer userId);
	
	public Order createOrder(OrderDto orderDto) throws Exception;
	
	public Order updateStatus(Integer orderId, Integer statusNumber);
	
	public Order updateToken(Integer orderId, String token);
	
	public Order cancelOrder(Integer orderId) throws Exception;
}
