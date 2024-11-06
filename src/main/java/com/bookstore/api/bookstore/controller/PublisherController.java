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

import com.bookstore.api.bookstore.dto.CategoryDto;
import com.bookstore.api.bookstore.dto.PublisherDto;
import com.bookstore.api.bookstore.entity.Category;
import com.bookstore.api.bookstore.entity.Publisher;
import com.bookstore.api.bookstore.service.PublisherService;

@RestController
public class PublisherController {
	@Autowired
	private PublisherService publisherService;

	@GetMapping(value = "api/publisher")
	public List<Publisher> getPublisher() {
		return publisherService.findAll();
	}
	
	@PostMapping(value = "api/publisher")
	public Publisher postData(@RequestBody PublisherDto publisherDto) {
		return publisherService.create(publisherDto);
	}
	
	@PutMapping(value = "api/publisher")
	public Publisher putData(@RequestBody PublisherDto publisherDto) {
		return publisherService.update(publisherDto);
	}
	
	@DeleteMapping(value = "api/publisher/{id}")
	public void delete(@PathVariable Integer id) {
		publisherService.delete(id);
	}
}
