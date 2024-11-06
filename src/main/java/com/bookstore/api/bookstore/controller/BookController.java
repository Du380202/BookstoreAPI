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

import com.bookstore.api.bookstore.dto.BookDto;
import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Publisher;
import com.bookstore.api.bookstore.service.BookService;
import com.bookstore.api.bookstore.service.PublisherService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping(value = "/api/book")
	public List<Book> getBooks() {
		List<Book> result = bookService.findAll();
		System.out.println(result.size());
		return result;
	}

	@PostMapping(value = "/api/book")
	public Book createNewBook(@RequestBody BookDto bookDto) {
		System.out.print(bookDto.getCategoryIds());
		Book b = bookService.createNewBook(bookDto);
		return b;
	}
	
	@PutMapping(value = "/api/book")
	public Book updateBook(@RequestBody BookDto bookDto) {
		Book b = bookService.createNewBook(bookDto);
		return b;
	}

	@DeleteMapping(value = "/api/book/{ids}")
	public void deleteBook(@PathVariable Integer ids) {
		bookService.deleteBook(ids);
	}

}