package com.bookstore.api.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.dto.OrderDto;
import com.bookstore.api.bookstore.entity.Cart;
import com.bookstore.api.bookstore.entity.Order;
import com.bookstore.api.bookstore.entity.User;
import com.bookstore.api.bookstore.repository.OrderRepository;
import com.bookstore.api.bookstore.repository.UserRepository;
import com.bookstore.api.bookstore.service.CartService;
import com.bookstore.api.bookstore.service.OrderDetailService;
import com.bookstore.api.bookstore.service.OrderService;
import com.bookstore.api.bookstore.service.UserService;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserRepository userService;
	
	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}
	
	@Override
	public List<Order> findAllByStatus(Integer status) {
		// TODO Auto-generated method stub
		return orderRepository.findAllByStatus(status);
	}
	@Override
	public List<Order> findAllByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return orderRepository.findAllByUser_UserId(userId);
	}
	
	@Override
	public Order createOrder(OrderDto orderDto) throws Exception {
		Order order = new Order();
		User user = userService.findById(orderDto.getUserId()).get();
		order.setFullName(orderDto.getFullName());
		order.setAddress(orderDto.getAddress());
		order.setEmail(orderDto.getEmail());
		order.setOrderDate(orderDto.getOrderDate());
		order.setStatus(orderDto.getStatus());
		order.setUser(user);
		if (!orderDto.getToken().isEmpty()) {
			order.setToken(orderDto.getToken());
		}
		
		List<Cart> carts = cartService.findAllByUser(orderDto.getUserId());
		if (!carts.isEmpty()) {
			BigDecimal totalPrice = BigDecimal.ZERO;
			for (Cart cart : carts) {
				totalPrice = totalPrice.add(cart.getTotalPrice());
				cartService.deleteById(cart.getCartId());
			}
			order.setTotalPrice(totalPrice);
			Order newOrder = orderRepository.save(order);
			orderDetailService.createOrderDetail(carts,newOrder);
			return newOrder;
		}
		else {
			throw new DataIntegrityViolationException("Your cart is empty");
		}
		
		
	}

	@Override
	public Order updateStatus(Integer orderId, Integer statusNumber) {
		Order order = orderRepository.findById(orderId).get();
		order.setStatus(statusNumber);
		return orderRepository.save(order);
	}

	@Override
	public Order cancelOrder(Integer orderId) throws Exception {
		Order order = orderRepository.findById(orderId).get();
		if (order.getStatus() == 1) {
			order.setStatus(0);
			return orderRepository.save(order);
		}
		else {
			throw new Exception("Do not cancel this order because it is shipping");
		}
		
	}
	
	
	
	

}
