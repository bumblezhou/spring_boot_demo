package com.example.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springboot.demo.service.DepartmentService;

@Controller
public class DepartmentController {

    @Autowired private DepartmentService departmentService;

    @GetMapping("/departments")
    public String departments(Model model){

        // var departments = departmentService.fetchDepartmentList();
        // model.addAttribute("departments", departments);

        return "departments";
    }
}
