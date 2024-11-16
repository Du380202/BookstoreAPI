package com.bookstore.api.bookstore.service;

import java.io.IOException;

import com.bookstore.api.bookstore.dto.LoginDto;
import com.bookstore.api.bookstore.dto.UserDto;
import com.bookstore.api.bookstore.entity.User;

public interface UserService {
	public User createUser(UserDto userDto) throws IOException;
	
	public User login(LoginDto loginDto) throws Exception;

}
