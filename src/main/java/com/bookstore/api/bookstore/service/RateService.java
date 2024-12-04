package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.RateDto;
import com.bookstore.api.bookstore.entity.Rate;

public interface RateService {
	public Rate createRate(RateDto rateDto);
	
	public List<Rate> getRateByBookId(Integer bookId);
	
	

}
