package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.bookstore.api.bookstore.dto.BookDto;
import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Category;
import com.bookstore.api.bookstore.entity.Publisher;
import com.bookstore.api.bookstore.repository.BookRepository;
import com.bookstore.api.bookstore.service.BookService;
import com.bookstore.api.bookstore.service.CategoryService;
import com.bookstore.api.bookstore.service.PublisherService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private CategoryService categoryService;
	
	
	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book createNewBook(BookDto bookDto) {
		Book newBook = new Book();
		List<Category> categories = categoryService.findAllById(bookDto.getCategoryIds());
		newBook.setBookName(bookDto.getBookName());
		newBook.setDescription(bookDto.getDescription());
		newBook.setImage(bookDto.getImage());
		newBook.setPrice(bookDto.getPrice());
		newBook.setQuantity(bookDto.getQuantity());
		newBook.setStatus(bookDto.getStatus());
		newBook.setRating(bookDto.getRating());
		newBook.setPublicationYear(bookDto.getPublicationYear());
		Publisher publi = publisherService.findById(bookDto.getPublisherId());
		newBook.setPublisher(publi);
		newBook.setCategories(categories);
		Book book = bookRepository.save(newBook);
		return book;
	}

	@Override
	public Book updateBook(BookDto bookDto) {
		Book book = bookRepository.findById(bookDto.getBookId()).get();
		book.setBookName(bookDto.getBookName());
		book.setDescription(bookDto.getDescription());
		book.setImage(bookDto.getImage());
		book.setPrice(bookDto.getPrice());
		book.setQuantity(bookDto.getQuantity());
		book.setStatus(bookDto.getStatus());
		book.setRating(bookDto.getRating());
		book.setPublicationYear(bookDto.getPublicationYear());
		Publisher publi = publisherService.findById(bookDto.getPublisherId());
		book.setPublisher(publi);
		bookRepository.save(book);
		return book;
	}

	@Override
	public void deleteBook(Integer ids) {
		bookRepository.deleteById(ids);

	}

}
