package com.bookstore.api.bookstore.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class UploadService {
	private final Cloudinary cloudinary;

    public UploadService() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dl20z53q6",
            "api_key", "281493374615111",
            "api_secret", "CT0_z_PHXzuyixwWc0RbaAb4aOs"
        ));
    }

    public String uploadToCloudinary(MultipartFile image) throws IOException {
    	Map uploadResult = cloudinary.uploader().upload(image.getBytes(),
                ObjectUtils.asMap(
                    "resource_type", "auto",
                    "folder", "book"
                ));
        return uploadResult.get("url").toString();
    }
}
