package com.bookstore.api.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.dto.CartDto;
import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Cart;
import com.bookstore.api.bookstore.entity.User;
import com.bookstore.api.bookstore.repository.BookRepository;
import com.bookstore.api.bookstore.repository.CartRepository;
import com.bookstore.api.bookstore.repository.UserRepository;
import com.bookstore.api.bookstore.service.CartService;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Cart addCart(CartDto cartDto) {
		Cart cart = cartRepository.findByBookIdAndUserId(cartDto.getBookid(), cartDto.getUserid());
		if(cart != null) {
			int quantity = cart.getQuantity() + 1;
			cart.setQuantity(quantity);
		}
		else {
			cart = new Cart();
			Book book = bookRepository.findById(cartDto.getBookid()).get(); 
			User user = userRepository.findById(cartDto.getUserid()).get();
			BigDecimal totalPrice = book.getPrice().multiply(BigDecimal.valueOf(cartDto.getQuantity()));
			cart.setQuantity(cartDto.getQuantity());
			cart.setTotalPrice(totalPrice);
			cart.setBook(book);
			cart.setUser(user);
			
			
		}
		return cartRepository.save(cart);
		
	}

	@Override
	public Cart findAllById(Integer bookId, Integer userId) {
		return cartRepository.findByBookIdAndUserId(bookId, userId);
	}

}
