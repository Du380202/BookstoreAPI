package com.bookstore.api.bookstore.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bookstore.api.bookstore.dto.BookDto;
import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Publisher;
import com.bookstore.api.bookstore.service.BookService;
import com.bookstore.api.bookstore.service.PublisherService;
import com.bookstore.api.bookstore.service.UploadService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UploadService uploadService;

	@GetMapping(value = "/api/book")
	public List<Book> getBooks() {
		List<Book> result = bookService.findAll();
		System.out.println(result.size());
		return result;
	}
	
	@GetMapping(value = "/api/book/search")
	public List<Book> searchBook(@RequestParam("keyword") String keyword) {
		return bookService.searchBook(keyword);
	}
	
	@PostMapping(value = "api/book")
	public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile imgFile, @RequestPart BookDto bookDto) throws IOException {
		String imageUrl = uploadService.uploadToCloudinary(imgFile, "book");
		bookDto.setImage(imageUrl);
		return ResponseEntity.ok(bookService.createNewBook(bookDto));
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
