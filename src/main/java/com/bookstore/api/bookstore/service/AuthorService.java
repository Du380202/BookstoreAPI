package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.AuthorDto;
import com.bookstore.api.bookstore.entity.Author;

public interface AuthorService {
	public List<Author> findAuthorById(List<Integer> ids);
	
	public List<Author> findAll();
	
	public Author create(AuthorDto authorDto);
	
	public Author update(AuthorDto authorDto);
	
	public String deleteAuthor(Integer ids) throws Exception;
	
}
