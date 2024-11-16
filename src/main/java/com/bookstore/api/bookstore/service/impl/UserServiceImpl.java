package com.bookstore.api.bookstore.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.dto.LoginDto;
import com.bookstore.api.bookstore.dto.UserDto;
import com.bookstore.api.bookstore.entity.Role;
import com.bookstore.api.bookstore.entity.User;
import com.bookstore.api.bookstore.repository.RoleRepository;
import com.bookstore.api.bookstore.repository.UserRepository;
import com.bookstore.api.bookstore.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	RoleRepository roleRepository;
	
	@Override
	public User createUser(UserDto userDto) throws IOException {
		String userName = userDto.getUsername();
		if (userRepository.existsByUsername(userName)) {
			throw new DataIntegrityViolationException("Username allready exists");
		}
		Role role = roleRepository.findById(userDto.getRoleId()).get();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		String password = passwordEncoder.encode(userDto.getPassword());
		User user = new User();
		user.setAddress(userDto.getAddress());
		user.setEmail(userDto.getEmail());
		user.setFullName(userDto.getFullName());
		user.setPassword(password);
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setUsername(userDto.getUsername());
		user.setRole(role);
		user.setStatus(1);
		return userRepository.save(user);
	}

	@Override
	public User login(LoginDto loginDto) throws Exception {
		User user = userRepository.findByUsername(loginDto.getUsername());
		if (user == null) {
			throw new DataIntegrityViolationException("Username not found");
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			throw new DataIntegrityViolationException("Wrong username or password");
		}
		
		return user;
	}

}
