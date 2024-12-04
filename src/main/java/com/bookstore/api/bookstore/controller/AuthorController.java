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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.api.bookstore.dto.AuthorDto;
import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.entity.Author;
import com.bookstore.api.bookstore.model.ApiResponse;
import com.bookstore.api.bookstore.service.AuthorService;
import com.bookstore.api.bookstore.service.UploadService;

@RestController
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private UploadService uploadService;
	
	@GetMapping(value = "api/author")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(authorService.findAll())  ;
	}
	
	@PostMapping(value = "api/author")
	public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile imgFile,
			@RequestPart AuthorDto authorDto) throws IOException {
		String imageUrl = uploadService.uploadToCloudinary(imgFile, "author");
		authorDto.setAuthorImg(imageUrl);
		return ResponseEntity.ok(authorService.create(authorDto));
	}
	
	@PutMapping(value = "api/author")
	public ResponseEntity<?> putData(@RequestPart(value = "image", required = false) MultipartFile imgFile,
			@RequestPart AuthorDto authorDto) throws IOException {
		String imageUrl = "";
		if (imgFile != null && !imgFile.isEmpty()) {
			imageUrl = uploadService.uploadToCloudinary(imgFile, "author");
			authorDto.setAuthorImg(imageUrl);
		}
		System.out.print(imageUrl);
		return ResponseEntity.ok(authorService.update(authorDto));
	}
	
	@DeleteMapping(value = "api/author/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(new 
					ApiResponse(HttpStatus.OK.value(), authorService.deleteAuthor(id)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new 
					ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
}
