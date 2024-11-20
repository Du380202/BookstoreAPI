package com.bookstore.api.bookstore.dto;

import java.math.BigDecimal;

public class CartViewDto {
	private Integer cartId;
	private Integer bookid;
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal totalPrice;
	private String imgUrl;
	private String bookName;
}
