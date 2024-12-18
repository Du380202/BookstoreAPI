package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.entity.Category;
import com.bookstore.api.bookstore.model.ApiResponse;

public interface CategoryService {
	public List<Category> findAll();
	
	public List<Category> findAllByIds(List<Integer> ids);
	
	public Category create(CategoryDto categoryDto);
	
	public Category update(CategoryDto categoryDto);
	
	public String delete(Integer id) throws Exception;
}
