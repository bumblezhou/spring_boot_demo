package com.example.springboot.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// import com.example.springboot.demo.service.DepartmentService;

@Controller 
public class MvpController {

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("message", "Hello, Spring MVC with Thymeleaf!");
        model.addAttribute("username", "JohnDoe");

        return "index";
    }

    // @Autowired private DepartmentService departmentService;
    @GetMapping("/departments_js")
    public String departments_js(Model model){

        // var departments = departmentService.fetchDepartmentList();
        // model.addAttribute("departments", departments);

        return "departments_js";
    }

    // @Autowired private DepartmentService departmentService;
    @GetMapping("/departments_vue")
    public String departments_vue(Model model){

        // var departments = departmentService.fetchDepartmentList();
        // model.addAttribute("departments", departments);

        return "departments_vue";
    }

    @GetMapping("/organizations")
    public String procurements(Model model){
        return "organizations";
    }

    @GetMapping("/products")
    public String products(Model model){
        return "products";
    }
}