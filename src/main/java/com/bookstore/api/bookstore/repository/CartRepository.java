package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
//	@Query("SELECT c FROM Cart c ") 
//	List<Cart> findByBookIdAndUserId(Integer bookId, Integer userId);
	@Query("SELECT c FROM Cart c WHERE c.book.bookId = :bookId AND c.user.userId = :userId")
    Cart findByBookIdAndUserId(@Param("bookId") Integer bookId, @Param("userId") Integer userId);
	
	@Query("SELECT c FROM Cart c WHERE c.user.userId = :userId")
    List<Cart> findAllCartByUserId(@Param("userId") Integer userId);

}
