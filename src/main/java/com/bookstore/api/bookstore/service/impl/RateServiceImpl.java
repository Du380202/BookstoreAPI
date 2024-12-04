package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.dto.RateDto;
import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Rate;
import com.bookstore.api.bookstore.entity.User;
import com.bookstore.api.bookstore.repository.BookRepository;
import com.bookstore.api.bookstore.repository.RateRepository;
import com.bookstore.api.bookstore.repository.UserRepository;
import com.bookstore.api.bookstore.service.RateService;
@Service
public class RateServiceImpl implements RateService {
	@Autowired
	private RateRepository rateRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Rate createRate(RateDto rateDto) {
		Rate rate = new Rate();
		User user = userRepository.findById(rateDto.getUserId()).get();
		Book book = bookRepository.findById(rateDto.getBookId()).get();
		book.setRating(null);
		rate.setDate(rateDto.getDate());
		rate.setComment(rateDto.getComment());
		rate.setStart(rateDto.getStart());
		rate.setUser(user);
		rate.setBook(book);
		
		return rateRepository.save(rate);
	}

	@Override
	public List<Rate> getRateByBookId(Integer bookId) {
		
		return rateRepository.findAllRateByBook(bookId);
	}

}
