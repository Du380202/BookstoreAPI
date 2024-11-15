package com.bookstore.api.bookstore.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.api.bookstore.service.UploadService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@RestController
public class UploadCotroller {
	@Autowired
	private UploadService uploadService;
	@PostMapping(value = "api/upload")
	public String uploadImage(@RequestPart("image") MultipartFile imgFile) throws IOException {
		return uploadService.uploadToCloudinary(imgFile);
	}
	
}
