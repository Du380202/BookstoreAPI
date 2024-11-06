package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Publisher;
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	public Publisher findById(int id);
	List<Publisher> findAll();
}
