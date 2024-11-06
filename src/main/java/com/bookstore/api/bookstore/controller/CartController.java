package com.bookstore.api.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.dto.CartDto;
import com.bookstore.api.bookstore.entity.Cart;
import com.bookstore.api.bookstore.service.CartService;

@RestController
public class CartController {
	@Autowired
	private CartService cartService;
	
	@GetMapping(value = "api/cart")
	public Cart getByCart() {
		return cartService.findAllById(1,1);
	}
	
	@PostMapping("api/cart")
	public Cart postData(@RequestBody CartDto cartDto) {
		return cartService.addCart(cartDto);
	}
 }
