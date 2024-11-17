package com.bookstore.api.bookstore.service;

import java.util.List;

import com.bookstore.api.bookstore.entity.Cart;
import com.bookstore.api.bookstore.entity.Order;

public interface OrderDetailService {
	public boolean createOrderDetail(List<Cart> carts, Order order);
}
