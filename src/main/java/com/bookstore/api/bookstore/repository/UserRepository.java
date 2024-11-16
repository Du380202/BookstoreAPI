package com.bookstore.api.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUsername(String object);
	
	public User findByUsername(String username);

}
