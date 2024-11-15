package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.dto.AuthorDto;
import com.bookstore.api.bookstore.entity.Author;
import com.bookstore.api.bookstore.repository.AuthorRepository;
import com.bookstore.api.bookstore.service.AuthorService;
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
	public void deleteAuthor(Integer ids) {
		authorRepository.deleteById(ids);
		
	}
	@Override
	public List<Author> findAll() {
		return authorRepository.findAll();
	}
	

}
