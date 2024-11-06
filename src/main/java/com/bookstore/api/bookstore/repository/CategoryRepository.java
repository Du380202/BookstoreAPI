package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public List<Category> findAllByCategoryIdIn(List<Integer> ids);
}
