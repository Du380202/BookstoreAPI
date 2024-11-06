package com.bookstore.api.bookstore.dto;

public class CategoryDto {
	private Integer categoryId;
	private String categoryName;
	private String depscription;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDepscription() {
		return depscription;
	}
	public void setDepscription(String depscription) {
		this.depscription = depscription;
	}
	
	
}
