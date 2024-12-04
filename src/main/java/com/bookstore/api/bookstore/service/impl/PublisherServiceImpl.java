package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.dto.PublisherDto;
import com.bookstore.api.bookstore.entity.Publisher;
import com.bookstore.api.bookstore.repository.PublisherRepository;
import com.bookstore.api.bookstore.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public Publisher findById(int id) {

        return publisherRepository.findById(id);
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

	@Override
	public Publisher create(PublisherDto publisherDto) {
		Publisher publisher = new Publisher();
		publisher.setAddress(publisherDto.getAddress());
		publisher.setEmail(publisherDto.getEmail());
		publisher.setHotline(publisherDto.getHotline());
		publisher.setPublisherName(publisherDto.getPublishname());
		return publisherRepository.save(publisher);
	}

	@Override
	public Publisher update(PublisherDto publisherDto) {
		Publisher publisher = publisherRepository.findById(publisherDto.getPublisherId()).get();
		publisher.setAddress(publisherDto.getAddress());
		publisher.setEmail(publisherDto.getEmail());
		publisher.setHotline(publisherDto.getHotline());
		publisher.setPublisherName(publisherDto.getPublishname());
		return publisherRepository.save(publisher);
	}

	@Override
	public void delete(Integer id) {
		publisherRepository.deleteById(id);
		
	}

}
