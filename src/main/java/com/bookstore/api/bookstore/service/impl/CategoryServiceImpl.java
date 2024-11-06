package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.entity.Category;
import com.bookstore.api.bookstore.repository.CategoryRepository;
import com.bookstore.api.bookstore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAllById(List<Integer> ids) {
		
		return categoryRepository.findAllById(ids);
	}

	@Override
	public Category create(CategoryDto categoryDto) {
		Category category = new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setDepscription(categoryDto.getDepscription());
		return categoryRepository.save(category);
	}

	@Override
	public Category update(CategoryDto categoryDto) {
		Category category = categoryRepository.findById(categoryDto.getCategoryId()).get();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setDepscription(categoryDto.getDepscription());
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		categoryRepository.deleteById(id);
		
	}

}
