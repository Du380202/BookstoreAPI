package com.bookstore.api.bookstore.dto;

import java.math.BigDecimal;

import com.bookstore.api.bookstore.entity.Book;
import com.bookstore.api.bookstore.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CartDto {
	private Integer cartId;
	private Integer quantity;
	private BigDecimal price;
    private Integer bookid;
    private Integer userid;
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal totalPrice) {
		this.price = totalPrice;
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
    
    
}
