package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	public List<Author> findAllByAuthorIdIn(List<Integer> ids);
}
