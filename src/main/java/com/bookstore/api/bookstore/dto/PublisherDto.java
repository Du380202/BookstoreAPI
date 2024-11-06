package com.bookstore.api.bookstore.dto;

public class PublisherDto {
	private Integer publisherId;
	private String publishname;
	private String address;
	private String hotline;
	private String email;

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublishname() {
		return publishname;
	}

	public void setPublishname(String publishname) {
		this.publishname = publishname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
