package com.bookstore.api.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Discount;
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
