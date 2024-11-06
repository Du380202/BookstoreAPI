package com.bookstore.api.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.entity.Category;
import com.bookstore.api.bookstore.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value ="api/category")
	public List<Category> getAll(@RequestParam List<Integer> ids) {
		return categoryService.findAllById(ids);
	}
	
	@PostMapping(value = "api/category")
	public Category postData(@RequestBody CategoryDto categoryDto) {
		return categoryService.create(categoryDto);
	}
	
	@PutMapping(value = "api/category")
	public Category putData(@RequestBody CategoryDto categoryDto) {
		return categoryService.update(categoryDto);
	}
	
	@DeleteMapping(value = "api/category/{id}")
	public void delete(@PathVariable Integer id) {
		categoryService.delete(id);
	}
	
}
