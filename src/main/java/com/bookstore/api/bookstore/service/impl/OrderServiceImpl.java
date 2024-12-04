package com.bookstore.api.bookstore.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.code.MessageCode;
import com.bookstore.api.bookstore.dto.OrderDto;
import com.bookstore.api.bookstore.dto.RevenueDto;
import com.bookstore.api.bookstore.entity.Cart;
import com.bookstore.api.bookstore.entity.Order;
import com.bookstore.api.bookstore.entity.OrderDetail;
import com.bookstore.api.bookstore.entity.User;
import com.bookstore.api.bookstore.repository.OrderRepository;
import com.bookstore.api.bookstore.repository.UserRepository;
import com.bookstore.api.bookstore.service.BookService;
import com.bookstore.api.bookstore.service.CartService;
import com.bookstore.api.bookstore.service.OrderDetailService;
import com.bookstore.api.bookstore.service.OrderService;
import com.bookstore.api.bookstore.service.ReadMessageService;
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
	
	@Autowired
	private BookService bookService;
	
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
		
		order.setOrderDate(LocalDate.now());
		order.setStatus(orderDto.getStatus());
		order.setUser(user);
		
		List<Cart> carts = cartService.findAllByUser(orderDto.getUserId());
		if (!carts.isEmpty()) {
			BigDecimal totalPrice = BigDecimal.ZERO;
			for (Cart cart : carts) {
				totalPrice = totalPrice.add(cart.getTotalPrice());
				cartService.deleteById(cart.getCartId());
				int quantity = 0 - cart.getQuantity();
				bookService.updateQuantity(cart.getBook().getBookId(), quantity);
			}
			order.setTotalPrice(totalPrice);
			Order newOrder = orderRepository.save(order);
			orderDetailService.createOrderDetail(carts,newOrder);
			return newOrder;
		}
		else {
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.ERROR_MESSAGE));
		}
		
		
	}

	@Override
	public String updateStatus(Integer orderId, Integer statusNumber) throws Exception {
		try {
			Order order = orderRepository.findById(orderId).get();
			order.setStatus(statusNumber);
			orderRepository.save(order);
			return ReadMessageService.KeyValueStore.get(MessageCode.SUCCESS_MESSAGE);
		}catch (Exception e) {
			throw new Exception(ReadMessageService.KeyValueStore.get(MessageCode.ERROR_MESSAGE));
		}
		
	}

	@Override
	public Order cancelOrder(Integer orderId) throws Exception {
		Order order = orderRepository.findById(orderId).get();
		if (order.getStatus() == 0) {
			List<OrderDetail> orderDetails = orderDetailService.getOrderDetail(orderId);
			for (OrderDetail orderDetail : orderDetails) {
				bookService.updateQuantity(orderDetail.getBook().getBookId(), orderDetail.getQuantity());
			}
			order.setStatus(3);
			return orderRepository.save(order);
		}
		else {
			throw new Exception(ReadMessageService.KeyValueStore.get(MessageCode.ERROR_MESSAGE));
		}
		
	}

	@Override
	public Order updateToken(Integer orderId, String token) {
		Order order = orderRepository.findById(orderId).get();
		order.setToken(token);
		return orderRepository.save(order);
	}

	@Override
	public List<RevenueDto> getRevenueByMonth(int year) {
		List<Object[]> results = orderRepository.findRevenueByMonthAndStatus(year, 2);
        List<RevenueDto> revenueList = new ArrayList<>();
        
        for (Object[] result : results) {
            int month = (int) result[0];
            BigDecimal totalRevenue = (BigDecimal) result[1];
            revenueList.add(new RevenueDto(month, totalRevenue));
        }
        
        return revenueList;
	}
}
