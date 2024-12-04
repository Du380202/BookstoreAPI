package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.code.MessageCode;
import com.bookstore.api.bookstore.dto.AuthorDto;
import com.bookstore.api.bookstore.entity.Author;
import com.bookstore.api.bookstore.repository.AuthorRepository;
import com.bookstore.api.bookstore.service.AuthorService;
import com.bookstore.api.bookstore.service.ReadMessageService;
@Service
public class AuthorsServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	@Override
	public List<Author> findAuthorById(List<Integer> ids) {
		
		return authorRepository.findAllByAuthorIdIn(ids);
	}
	@Override
	public Author create(AuthorDto authorDto) {
		Author author = new Author();
		author.setAuthorName(authorDto.getAuthorName());
		author.setBiography(authorDto.getBiography());
		author.setAuthorImg(authorDto.getAuthorImg());
		return authorRepository.save(author);
	}
	
	@Override
	public Author update(AuthorDto authorDto) {
		Author author = authorRepository.findById(authorDto.getAuthorId()).get();
		author.setAuthorName(authorDto.getAuthorName());
		author.setBiography(authorDto.getBiography());
		
		author.setAuthorImg(authorDto.getAuthorImg());
		return authorRepository.save(author);
	}
	
	@Override
	public String deleteAuthor(Integer ids) throws Exception {
		try {
			authorRepository.deleteById(ids);
			return ReadMessageService.KeyValueStore.get(MessageCode.SUCCESS_MESSAGE);
		} catch (Exception e) {
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.FOREIGN_KEY_EXCEPTION));
		}
		
	}
	@Override
	public List<Author> findAll() {
		return authorRepository.findAll();
	}
	

}
