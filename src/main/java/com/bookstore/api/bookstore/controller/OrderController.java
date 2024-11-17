package com.bookstore.api.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.dto.OrderDto;
import com.bookstore.api.bookstore.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orService;
	
	@PostMapping(value = "api/order")
	public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
		try {
			return ResponseEntity.ok(orService.createOrder(orderDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
		}
		
	}
}