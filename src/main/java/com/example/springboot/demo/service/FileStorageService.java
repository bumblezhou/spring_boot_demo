package com.example.springboot.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    // Inject the value from application.properties
    @Value("${file.upload-dir}")
    private String uploadDir;

    public Path getUploadPath() {
        // Get the configured directory
        return Paths.get(uploadDir);
    }
}
