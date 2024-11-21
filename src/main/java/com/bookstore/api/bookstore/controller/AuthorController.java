package com.bookstore.api.bookstore.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.dto.AuthorDto;
import com.bookstore.api.bookstore.entity.Author;
import com.bookstore.api.bookstore.service.AuthorService;

@RestController
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@GetMapping(value = "api/author")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(authorService.findAll())  ;
	}
	
	@PostMapping(value="api/author")
	public Author postData(@RequestBody AuthorDto authorDto) {
		return authorService.create(authorDto);
	}
	
	@PutMapping(value = "api/author")
	public Author putData(@RequestBody AuthorDto authorDto) {
		return authorService.update(authorDto);
	}
	
	@DeleteMapping(value = "api/author/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(authorService.deleteAuthor(id)) ;
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
