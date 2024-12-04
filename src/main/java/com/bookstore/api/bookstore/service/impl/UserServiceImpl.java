package com.bookstore.api.bookstore.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.code.MessageCode;
import com.bookstore.api.bookstore.dto.LoginDto;
import com.bookstore.api.bookstore.dto.PasswordDto;
import com.bookstore.api.bookstore.dto.UserDto;
import com.bookstore.api.bookstore.entity.Role;
import com.bookstore.api.bookstore.entity.User;
import com.bookstore.api.bookstore.repository.RoleRepository;
import com.bookstore.api.bookstore.repository.UserRepository;
import com.bookstore.api.bookstore.service.ReadMessageService;
import com.bookstore.api.bookstore.service.UserService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	RoleRepository roleRepository;
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	
	@Override
	public User createUser(UserDto userDto) throws IOException {
		String userName = userDto.getUsername();
		if (userRepository.existsByUsername(userName)) {
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.NOT_FOUND));
		}
		Role role = roleRepository.findById(2).get();
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
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.NOT_FOUND));
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.ERROR_MESSAGE));
		}
		
		return user;
	}

	@Override
	public User chancePassword(PasswordDto passwordDto) throws Exception {
		User user = userRepository.findById(passwordDto.getUserId()).get();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		if (passwordEncoder.matches(passwordDto.getPassword(), user.getPassword())) {
			String password = passwordEncoder.encode(passwordDto.getNewPassword());
			user.setPassword(password);
			return userRepository.save(user);
		}
		else {
			throw new DataIntegrityViolationException(ReadMessageService.KeyValueStore.get(MessageCode.ERROR_MESSAGE));
		}
	}

	@Override
	public User updateUser(UserDto userDto) {
		if (userDto.getUserId() == null) {
			throw new IllegalArgumentException(ReadMessageService.KeyValueStore.get(MessageCode.NOT_FOUND));
		}
		
		User user = userRepository.findById(userDto.getUserId()).get();
		
		if (user == null) {
			throw new EntityNotFoundException(ReadMessageService.KeyValueStore.get(MessageCode.NOT_FOUND));
		}
		
		if (userDto.getFullName() != null) {
			user.setFullName(userDto.getFullName());
	    }
	    if (userDto.getEmail() != null) {
	    	user.setEmail(userDto.getEmail());
	    }
	    if (userDto.getPhoneNumber() != null) {
	    	user.setPhoneNumber(userDto.getPhoneNumber());
	    }
	    if (userDto.getAddress() != null) {
	    	user.setAddress(userDto.getAddress());
	    }
	    
	    return userRepository.save(user);
	}


}
