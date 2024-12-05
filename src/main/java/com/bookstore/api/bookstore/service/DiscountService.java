package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.DiscountDto;
import com.bookstore.api.bookstore.entity.Discount;

public interface DiscountService {
	public Discount createDiscount(DiscountDto discountDto);
	
	public List<Discount> findAll();
	
	public String cancelDiscount(Integer discountId);
	
}
