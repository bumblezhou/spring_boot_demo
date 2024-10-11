package com.example.springboot.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Return the name of the view or template to display for the error
        return "error"; // This should correspond to error.html or similar in your templates
    }

    // Optionally, override this method to return a custom error path if needed
    public String getErrorPath() {
        return "/error";
    }
}