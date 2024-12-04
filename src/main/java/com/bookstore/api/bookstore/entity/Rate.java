package com.bookstore.api.bookstore.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rate")
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rateid")
	private Integer rateId;
	@Column(name = "comment")
	private String comment;
	@Column(name = "start")
	private Float start;
	@Column(name = "date")
	private LocalDate date;
//	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "userid")
    private User user;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;
	public Integer getRateId() {
		return rateId;
	}
	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Float getStart() {
		return start;
	}
	public void setStart(Float start) {
		this.start = start;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}
