package com.bookstore.api.bookstore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name = "Category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryid")
	private Integer categoryId;
	@Column(name = "categoryname")
	private String categoryName;
	@Column(name = "depscription")
	private String depscription;
	@Column(name = "categoryimg")
	private String categoryImg;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	private List<Book> books = new ArrayList<>();
	
//	public List<Book> getBooks() {
//		return books;
//	}
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public String getCategoryImg() {
		return categoryImg;
	}
	public void setCategoryImg(String categoryImg) {
		this.categoryImg = categoryImg;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDepscription() {
		return depscription;
	}
	public void setDepscription(String depscription) {
		this.depscription = depscription;
	}
	
}
