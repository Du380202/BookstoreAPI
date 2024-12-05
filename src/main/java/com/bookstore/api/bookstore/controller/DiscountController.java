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
import com.bookstore.api.bookstore.dto.DiscountDto;
import com.bookstore.api.bookstore.model.ApiResponse;
import com.bookstore.api.bookstore.service.DiscountService;
import com.bookstore.api.bookstore.service.ReadMessageService;

@RestController
public class DiscountController {
	
	@Autowired
	private DiscountService discountService;
	
	@GetMapping("api/discount")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(discountService.findAll());
	}
	
	@PutMapping("api/discount")
	public ResponseEntity<?> cancelDiscount(@RequestParam Integer discountId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(new 
					ApiResponse(HttpStatus.OK.value(), discountService.cancelDiscount(discountId)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new 
					ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
	
	@PostMapping("api/discount")
	public ResponseEntity<?> createDiscount(@RequestBody DiscountDto discountDto) {
		return ResponseEntity.ok(discountService.createDiscount(discountDto));
	}

}
