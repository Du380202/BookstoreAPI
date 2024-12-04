package com.bookstore.api.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.dto.DiscountDto;
import com.bookstore.api.bookstore.service.DiscountService;

@RestController
public class DiscountController {
	
	@Autowired
	private DiscountService discountService;
	
	@GetMapping("api/discount")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(discountService.findAll());
	}
	
	@PostMapping("api/discount")
	public ResponseEntity<?> createDiscount(@RequestBody DiscountDto discountDto) {
		return ResponseEntity.ok(discountService.createDiscount(discountDto));
	}

}
