package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.BookDto;
import com.bookstore.api.bookstore.entity.Book;

public interface BookService {
	public List<Book> findAll();

	public Book createNewBook(BookDto bookDto);
	
	public void deleteBook(Integer ids);

	public Book updateBook(BookDto bookDto);
}
