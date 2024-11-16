package com.bookstore.api.bookstore.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.bookstore.dto.LoginDto;
import com.bookstore.api.bookstore.dto.UserDto;
import com.bookstore.api.bookstore.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value="api/register")
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws IOException {
		try {
			if (!userDto.getPassword().equals(userDto.getRetryPassword())) {
				return ResponseEntity.badRequest().body("Password not match");
			}
			
			return ResponseEntity.ok(userService.createUser(userDto)) ;
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping(value = "api/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
		try {
			return ResponseEntity.ok(userService.login(loginDto)) ;
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
