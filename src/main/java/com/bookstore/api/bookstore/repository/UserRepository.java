package com.bookstore.api.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.api.bookstore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
