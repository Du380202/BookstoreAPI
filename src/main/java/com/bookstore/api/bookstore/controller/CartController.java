package com.bookstore.api.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.code.MessageCode;
import com.bookstore.api.bookstore.dto.CartDto;
import com.bookstore.api.bookstore.entity.Cart;
import com.bookstore.api.bookstore.model.ApiResponse;
import com.bookstore.api.bookstore.service.CartService;
import com.bookstore.api.bookstore.service.ReadMessageService;

@RestController
public class CartController {
	@Autowired
	private CartService cartService;
	
	
	@GetMapping(value = "api/cart")
	public List<Cart> getCartByUser(@RequestParam("userId") Integer userId) {
		return cartService.findAllByUser(userId);  
	}
	
	@PostMapping("api/cart/add")
	public ResponseEntity<?> postData(@RequestParam("bookId") Integer bookId, 
			@RequestParam("userId") Integer userId) throws Exception {
		try {
			return ResponseEntity.ok(cartService.addNewCart(bookId, userId)) ;
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new 
					ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
	
	@PostMapping("api/cart/remove")
	public void removeCart(@RequestParam("cartId") Integer cartId) {
		cartService.removeCart(cartId);
	}
 }
