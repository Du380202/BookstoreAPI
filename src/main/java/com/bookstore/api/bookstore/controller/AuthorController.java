package com.bookstore.api.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Author> getAll() {
		return authorService.findAll();
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
	public void delete(@PathVariable Integer id) {
		authorService.deleteAuthor(id);
	}
}
