package com.bookstore.api.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.entity.Cart;
import com.bookstore.api.bookstore.entity.Order;
import com.bookstore.api.bookstore.entity.OrderDetail;
import com.bookstore.api.bookstore.repository.OrderDetailRepository;
import com.bookstore.api.bookstore.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public boolean createOrderDetail(List<Cart> carts, Order order) {
		for (Cart cart : carts) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(cart.getQuantity());
			orderDetail.setOrder(order);
			orderDetail.setBook(cart.getBook());
			orderDetail.setTotalPrice(cart.getTotalPrice());
			orderDetailRepository.save(orderDetail);
		}
		return false;
	}

}
