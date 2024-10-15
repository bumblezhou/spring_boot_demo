package com.example.springboot.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.demo.service.StaticResourceService;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private StaticResourceService staticResourceService;
    // Define the folder to store uploaded files
    // private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String upload_path = staticResourceService.getUploadsPath();
            // System.out.println(upload_path);
            // Create the directory if it doesn't exist
            // Path uploadPath = Paths.get("src", "main", "resources", "static", UPLOAD_DIR);
            Path uploadPath = Paths.get(upload_path);
            System.out.println(uploadPath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Get the original file name and save the file to the server
            @SuppressWarnings("null")
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Generate the file URL (you can adjust this URL as needed)
            String fileUrl = "/uploads/" + fileName;

            // Return the file URL in the response
            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
}
