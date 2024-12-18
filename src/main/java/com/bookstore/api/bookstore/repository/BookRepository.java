package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.Rate;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();
    
    public void deleteById(Integer id);
    
    @Query("select b from Book b Where b.bookName like %?1%")
	List<Book> searchBook(String key);
    
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.categoryId = :categoryId")
    List<Book> findAllBookByCategory(@Param("categoryId") Integer categoryId);
}
