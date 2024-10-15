package com.example.springboot.demo.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
public class StaticResourceService {

    public String getStaticResourcePath() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/");
        File file = resource.getFile();
        return file.getAbsolutePath();
    }

    public String getUploadsPath() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/uploads/");
        File file = resource.getFile();
        return file.getAbsolutePath();
    }
}
