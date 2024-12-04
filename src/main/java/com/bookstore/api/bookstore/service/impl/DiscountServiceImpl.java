package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.dto.DiscountDto;
import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Discount;
import com.bookstore.api.bookstore.repository.DiscountRepository;
import com.bookstore.api.bookstore.service.BookService;
import com.bookstore.api.bookstore.service.DiscountService;
@Service
public class DiscountServiceImpl implements DiscountService{
	@Autowired
	DiscountRepository discountRepository;
	
	@Autowired
	BookService bookService;

	@Override
	public Discount createDiscount(DiscountDto discountDto) {
		Discount discount = new Discount();
		List<Book> books = bookService.findAllByIds(discountDto.getBookIds());
		discount.setBooks(books);
		discount.setDescription(discountDto.getDescription());
		discount.setDiscountName(discountDto.getDiscountName());
		discount.setStartDate(discountDto.getStartDate());
		discount.setDiscountPercent(discountDto.getDiscountPercent());
		discount.setEndDate(discountDto.getEndDate());
		discount.setStatus(discountDto.getStatus());
		return discountRepository.save(discount);
	}

	@Override
	public List<Discount> findAll() {
		// TODO Auto-generated method stub
		return discountRepository.findAll();
	}

}
