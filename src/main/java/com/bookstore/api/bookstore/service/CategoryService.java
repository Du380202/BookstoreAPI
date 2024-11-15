package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.entity.Category;

public interface CategoryService {
	public List<Category> findAll();
	
	public List<Category> findAllByIds(List<Integer> ids);
	
	public Category create(CategoryDto categoryDto);
	
	public Category update(CategoryDto categoryDto);
	
	public void delete(Integer id);
}
