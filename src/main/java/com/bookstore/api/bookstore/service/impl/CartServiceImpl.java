package com.bookstore.api.bookstore.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.code.MessageCode;
import com.bookstore.api.bookstore.dto.CartDto;
import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Cart;
import com.bookstore.api.bookstore.entity.User;
import com.bookstore.api.bookstore.repository.BookRepository;
import com.bookstore.api.bookstore.repository.CartRepository;
import com.bookstore.api.bookstore.repository.UserRepository;
import com.bookstore.api.bookstore.service.CartService;
import com.bookstore.api.bookstore.service.ReadMessageService;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Cart addNewCart(Integer bookId, Integer userId) throws Exception {
		Cart cart = cartRepository.findByBookIdAndUserId(bookId, userId);
		Book book = bookRepository.findById(bookId).get(); 
		User user = userRepository.findById(userId).get();
		if(cart != null) {
			int quantity = cart.getQuantity() + 1;
			if(quantity <= book.getQuantity()) {
				cart.setQuantity(quantity);
				if (book.getDiscountedPrice().compareTo(BigDecimal.valueOf(0)) == 1)  {
					BigDecimal totalPrice = book.getDiscountedPrice().multiply(BigDecimal.valueOf(quantity));
					cart.setTotalPrice(totalPrice);
				}else {
					BigDecimal totalPrice = book.getPrice().multiply(BigDecimal.valueOf(quantity));
					cart.setTotalPrice(totalPrice);
				}
				
			}
			else {
				throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.QUANTITY_LIMIT));
			}
			
			
		}
		else {
			cart = new Cart();
			cart.setQuantity(1);
			if (book.getDiscountedPrice().compareTo(BigDecimal.valueOf(0)) == 1) {
				cart.setTotalPrice(book.getDiscountedPrice());
			} else {
				cart.setTotalPrice(book.getPrice());
			}
			
			cart.setBook(book);
			cart.setUser(user);
		}
		return cartRepository.save(cart);
		
	}

	@Override
	public Cart findAllById(Integer bookId, Integer userId) {
		return cartRepository.findByBookIdAndUserId(bookId, userId);
	}

	@Override
	public void removeCart(Integer cartId) {
		Cart cart = cartRepository.findById(cartId).get();
		if (cart.getQuantity() == 1) {
			cartRepository.delete(cart);
		}
		else {
			int quantity = cart.getQuantity();
			BigDecimal price = cart.getTotalPrice().divide(BigDecimal.valueOf(quantity), 2, RoundingMode.HALF_UP);
			quantity = quantity - 1;
			cart.setQuantity(quantity);
			BigDecimal totalPrice = cart.getTotalPrice().subtract(price);
			cart.setTotalPrice(totalPrice);
			cartRepository.save(cart);
		}
	}

	@Override
	public List<Cart> findAllByUser(Integer userId) {
		
		return cartRepository.findAllCartByUserId(userId);
	}

	@Override
	public void deleteById(Integer cartId) {
		cartRepository.deleteById(cartId);
		
	}

}
