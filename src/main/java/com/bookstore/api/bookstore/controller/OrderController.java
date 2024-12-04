package com.bookstore.api.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.code.MessageCode;
import com.bookstore.api.bookstore.dto.OrderDto;
import com.bookstore.api.bookstore.model.ApiResponse;
import com.bookstore.api.bookstore.service.OrderService;
import com.bookstore.api.bookstore.service.ReadMessageService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orService;
	@PostMapping(value = "api/order")
	public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
		try {
			orService.createOrder(orderDto);
			return ResponseEntity.status(HttpStatus.OK).body(new 
					ApiResponse(HttpStatus.OK.value(), ReadMessageService.KeyValueStore.get(MessageCode.SUCCESS_ORDER)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new 
					ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
		
	}
	
	@PutMapping(value = "api/order/update")
	public ResponseEntity<?> updateOrder(@RequestParam Integer orderId, @RequestParam Integer statusNumber) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(new 
					ApiResponse(HttpStatus.OK.value(), orService.updateStatus(orderId, statusNumber)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new 
					ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
		
	}
	
	@PostMapping(value = "api/order/cancel")
	public ResponseEntity<?> cancelOrder(@RequestParam Integer orderId) {
		try {
			orService.cancelOrder(orderId);
			return ResponseEntity.status(HttpStatus.OK).body(new 
					ApiResponse(HttpStatus.OK.value(), ReadMessageService.KeyValueStore.get(MessageCode.CANCEL_SUCCESS_ORDER)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new 
					ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
		
	}
	
	@PostMapping(value = "api/order/payment")
	public ResponseEntity<?> updateToken(@RequestParam Integer orderId, @RequestParam String statusNumber) {
		try {
			return ResponseEntity.ok(orService.updateToken(orderId, statusNumber));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
		}
		
	}
	
	@GetMapping(value = "api/order/status")
	public ResponseEntity<?> findAllByStatus(@RequestParam Integer status) {
		try {
			return ResponseEntity.ok(orService.findAllByStatus(status));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
		}
	}
	
	@GetMapping(value = "api/order/all")
	public ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok(orService.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
		}
	}
	
	@GetMapping(value = "api/order")
	public ResponseEntity<?> findAllByUser(@RequestParam Integer userId) {
		try {
			return ResponseEntity.ok(orService.findAllByUserId(userId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
		}
	}
	
	@GetMapping(value = "api/order/revenue")
	public ResponseEntity<?> getRevenue(@RequestParam int year) {
		try {
			return ResponseEntity.ok(orService.getRevenueByMonth(year));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
		}
	}
}
