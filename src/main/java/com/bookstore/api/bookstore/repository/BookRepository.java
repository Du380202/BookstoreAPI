package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();
    
    public void deleteById(Integer id);
    
    @Query("select b from Book b Where b.bookName like %?1%")
	List<Book> searchBook(String key);
}
