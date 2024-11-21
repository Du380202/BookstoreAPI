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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Publisher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="publisherid")
	private Integer publisherId;
	
	@Column(name = "publishername")
	private String publishname;
	@Column(name = "address")
	private String address;
	@Column(name = "hotline")
	private String hotline;
	@Column(name = "email")
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
	private List<Book> books = new ArrayList<Book>();
//	
//	public List<Book> getBooks() {
//		return books;
//	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublishname() {
		return publishname;
	}
	public void setPublishname(String publishname) {
		this.publishname = publishname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHotline() {
		return hotline;
	}
	public void setHotline(String hotline) {
		this.hotline = hotline;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	
	
}
