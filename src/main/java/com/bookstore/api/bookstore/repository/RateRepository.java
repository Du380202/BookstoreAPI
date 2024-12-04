package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
	@Query("SELECT r FROM Rate r WHERE r.book.bookId = :bookId")
    List<Rate> findAllRateByBook(@Param("bookId") Integer bookId);
//	List<Rate> findAllByBook_BookId(Integer bookId);
}
