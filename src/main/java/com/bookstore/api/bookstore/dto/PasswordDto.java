package com.bookstore.api.bookstore.dto;

public class PasswordDto {
	private Integer userId;
	private String password;
	private String newPassword;
	private String retryPassword;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRetryPassword() {
		return retryPassword;
	}
	public void setRetryPassword(String retryPassword) {
		this.retryPassword = retryPassword;
	}
	
}
