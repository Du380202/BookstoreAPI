package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.CartDto;
import com.bookstore.api.bookstore.entity.Cart;

public interface CartService {
	public Cart addNewCart(Integer bookId, Integer userId) ;
	
	public Cart findAllById(Integer bookId, Integer userId);
	
	public void removeCart(Integer cartId);
	
	public List<Cart> findAllByUser(Integer userId);
	
	public void deleteById(Integer cartId);
}
