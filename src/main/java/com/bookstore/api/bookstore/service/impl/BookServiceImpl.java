package com.bookstore.api.bookstore.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.bookstore.api.bookstore.code.MessageCode;
import com.bookstore.api.bookstore.dto.BookDto;
import com.bookstore.api.bookstore.entity.Author;
import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Category;
import com.bookstore.api.bookstore.entity.Publisher;
import com.bookstore.api.bookstore.repository.AuthorRepository;
import com.bookstore.api.bookstore.repository.BookRepository;
import com.bookstore.api.bookstore.service.BookService;
import com.bookstore.api.bookstore.service.CategoryService;
import com.bookstore.api.bookstore.service.PublisherService;
import com.bookstore.api.bookstore.service.ReadMessageService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book createNewBook(BookDto bookDto) {
		Book newBook = new Book();
		List<Category> categories = categoryService.findAllByIds(bookDto.getCategoryIds());
		List<Author> authors = authorRepository.findAllById(bookDto.getAuthorId());
		newBook.setBookName(bookDto.getBookName());
		newBook.setDescription(bookDto.getDescription());
		newBook.setImage(bookDto.getImage());
		newBook.setPrice(bookDto.getPrice());
		newBook.setDaySaleDate(bookDto.getDaySaleDate());
		newBook.setQuantity(bookDto.getQuantity());
		newBook.setStatus(bookDto.getStatus());
		newBook.setRating(bookDto.getRating());
		newBook.setPublicationYear(bookDto.getPublicationYear());
		Publisher publi = publisherService.findById(bookDto.getPublisherId());
		newBook.setPublisher(publi);
		newBook.setCategories(categories);
		newBook.setAuthors(authors);
		Book book = bookRepository.save(newBook);
		return book;
	}

	@Override
	public Book updateBook(BookDto bookDto) {
		Book book = bookRepository.findById(bookDto.getBookId()).get();
		List<Category> categories = categoryService.findAllByIds(bookDto.getCategoryIds());
		List<Author> authors = authorRepository.findAllById(bookDto.getAuthorId());
		if (bookDto.getBookName() != null && !bookDto.getBookName().trim().isEmpty()) {
		    book.setBookName(bookDto.getBookName());
		}

		if (bookDto.getPrice() != null) {
		    book.setPrice(bookDto.getPrice());
		}

		if (bookDto.getDescription() != null && !bookDto.getDescription().trim().isEmpty()) {
		    book.setDescription(bookDto.getDescription());
		}

		if (bookDto.getQuantity() > 0) {
		    book.setQuantity(bookDto.getQuantity());
		}

		if (bookDto.getImage() != null && !bookDto.getImage().trim().isEmpty()) {
		    book.setImage(bookDto.getImage());
		}

		if (bookDto.getPublicationYear() > 0) {
		    book.setPublicationYear(bookDto.getPublicationYear());
		}

		if (bookDto.getDaySaleDate() != null && !bookDto.getDaySaleDate().isAfter(LocalDate.now())) {
		    book.setDaySaleDate(bookDto.getDaySaleDate());
		}

		if (bookDto.getStatus() >= 0) {
		    book.setStatus(bookDto.getStatus());
		}

		if (bookDto.getPublisherId() > 0) {
		    Publisher publi = publisherService.findById(bookDto.getPublisherId());
		    if (publi != null) {
		        book.setPublisher(publi);
		    }
		}

		if (bookDto.getRating() >= 0.0f && bookDto.getRating() <= 5.0f) { 
		    book.setRating(bookDto.getRating());
		}
		book.setCategories(categories);
		book.setAuthors(authors);
		bookRepository.save(book);


		return book;
	}

	
	@Override
	public String delete(Integer ids) throws Exception {	
		try {
			bookRepository.deleteById(ids);
			return ReadMessageService.KeyValueStore.get(MessageCode.SUCCESS_MESSAGE);
		} catch (Exception e) {
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.FOREIGN_KEY_EXCEPTION));
		}
		
		
	}

	@Override
	public List<Book> searchBook(String key) {
		return bookRepository.searchBook(key);
	}

	@Override
	public void updateQuantity(Integer bookId, Integer quantity) {
		Book book = bookRepository.findById(bookId).get();
		int currentQuantity = book.getQuantity();
		currentQuantity = currentQuantity + quantity;
	
		
		System.out.print(currentQuantity);
		book.setQuantity(currentQuantity);
		bookRepository.save(book);
		
	}

	@Override
	public List<Book> getBookByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		return bookRepository.findAllBookByCategory(categoryId);
	}

	@Override
	public List<Book> findAllByIds(List<Integer> bookIds) {
		// TODO Auto-generated method stub
		return bookRepository.findAllById(bookIds);
	}

	@Override
	public Book findById(Integer bookId) {
		
		return bookRepository.findById(bookId).get();
	}

}
