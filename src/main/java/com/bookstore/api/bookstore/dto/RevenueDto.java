package com.bookstore.api.bookstore.dto;

import java.math.BigDecimal;

public class RevenueDto {
	private int month;
	private BigDecimal totalRevenue;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public RevenueDto(int month, BigDecimal totalRevenue) {
		super();
		this.month = month;
		this.totalRevenue = totalRevenue;
	}
	
	
}
