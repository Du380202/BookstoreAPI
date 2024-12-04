package com.bookstore.api.bookstore.dto;

import java.time.LocalDate;

public class RateDto {
	private Integer rateId;
	private String comment;
	private Float start;
	private LocalDate date;
	private Integer userId;
	private Integer bookId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
}
