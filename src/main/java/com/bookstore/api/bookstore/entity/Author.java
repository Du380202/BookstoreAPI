package com.bookstore.api.bookstore.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Author")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorid")
	private Integer authorId;
	
	@Column(name = "authorname")
	private String authorName;
	
	@Column(name = "biography", columnDefinition = "TEXT")
	private String biography;
	
	@Column(name = "authorimg")
	private String authorImg;

	public String getAuthorImg() {
		return authorImg;
	}

	public void setAuthorImg(String authorImg) {
		this.authorImg = authorImg;
	}
	@JsonIgnore
	@ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
	private List<Book> bookList = new ArrayList<>();
	
	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	
}
