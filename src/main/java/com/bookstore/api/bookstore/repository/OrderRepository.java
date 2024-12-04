package com.bookstore.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.api.bookstore.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	public List<Order> findAllByStatus(Integer status);
	
	public List<Order> findAllByUser_UserId(Integer userId);
	
	@Query("SELECT MONTH(o.orderDate) AS month, SUM(o.totalPrice) AS totalRevenue " +
	           "FROM Order o " +
	           "WHERE YEAR(o.orderDate) = :year AND o.status = :status " +
	           "GROUP BY MONTH(o.orderDate) " +
	           "ORDER BY month ASC")
	public List<Object[]> findRevenueByMonthAndStatus(@Param("year") int year, @Param("status") int status);

}
