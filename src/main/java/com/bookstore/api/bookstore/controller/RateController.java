package com.bookstore.api.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.dto.RateDto;
import com.bookstore.api.bookstore.service.RateService;

@RestController
public class RateController {
	@Autowired
	private RateService rateService;
	
	@PostMapping(value = "api/rate")
	public ResponseEntity<?> createRate(@RequestBody RateDto rateDto) {
		try {
			return ResponseEntity.ok(rateService.createRate(rateDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "api/rate")
	public ResponseEntity<?> findByBook(@RequestParam("bookId") Integer bookId) {
		try {
			return ResponseEntity.ok(rateService.getRateByBookId(bookId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
