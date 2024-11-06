package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.CartDto;
import com.bookstore.api.bookstore.entity.Cart;

public interface CartService {
	public Cart addCart(CartDto cartDto) ;
	
	public Cart findAllById(Integer bookId, Integer userId);
}
