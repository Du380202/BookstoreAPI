package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.code.MessageCode;
import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.entity.Category;
import com.bookstore.api.bookstore.repository.CategoryRepository;
import com.bookstore.api.bookstore.service.CategoryService;
import com.bookstore.api.bookstore.service.ReadMessageService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category create(CategoryDto categoryDto) {
		Category category = new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setDepscription(categoryDto.getDepscription());
		category.setCategoryImg(categoryDto.getCategoryImg());
		return categoryRepository.save(category);
	}

	@Override
	public Category update(CategoryDto categoryDto) {
		Category category = categoryRepository.findById(categoryDto.getCategoryId()).get();
		if (categoryDto.getCategoryImg() != null) {
			category.setCategoryImg(categoryDto.getCategoryImg());
		}
		
		if (categoryDto.getCategoryName()!= null) {
			category.setCategoryName(categoryDto.getCategoryName());
		}
		if (categoryDto.getDepscription() != null) {
			category.setDepscription(categoryDto.getDepscription());
		}
		
		return categoryRepository.save(category);
	}

	@Override
	public String delete(Integer id) throws Exception {
		if (!categoryRepository.existsById(id)) {
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.NOT_FOUND));
		}
		
		try {
			categoryRepository.deleteById(id);
			return ReadMessageService.KeyValueStore.get(MessageCode.SUCCESS_MESSAGE);
		} catch (Exception e) {
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.FOREIGN_KEY_EXCEPTION));
		}
		
		
	}

	@Override
	public List<Category> findAllByIds(List<Integer> ids) {
		return categoryRepository.findAllById(ids);
	}

}
