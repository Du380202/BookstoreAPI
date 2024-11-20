package com.bookstore.api.bookstore.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.entity.Category;
import com.bookstore.api.bookstore.service.CategoryService;
import com.bookstore.api.bookstore.service.UploadService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UploadService uploadService;
	
	@GetMapping(value ="api/category")
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	
	@PostMapping(value = "api/category")
	public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile imgFile, @RequestPart CategoryDto categoryDto) throws IOException {
		String imageUrl = uploadService.uploadToCloudinary(imgFile, "category");
		categoryDto.setCategoryImg(imageUrl);
		return ResponseEntity.ok(categoryService.create(categoryDto));
	}
	
	@PutMapping(value = "api/category")
	public Category putData(@RequestBody CategoryDto categoryDto) {
		return categoryService.update(categoryDto);
	}
	
	@DeleteMapping(value = "api/category/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(categoryService.delete(id)) ;
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
