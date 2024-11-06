package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.api.bookstore.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();
    
    public void deleteById(Integer id);
}
