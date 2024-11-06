package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.dto.PublisherDto;
import com.bookstore.api.bookstore.entity.Publisher;

public interface PublisherService {
    public Publisher findById(int id);

    List<Publisher> findAll();
    
    public Publisher create(PublisherDto publisherDto);
    
    public Publisher update(PublisherDto publisherDto);
    
    public void delete(Integer id);
    
}
