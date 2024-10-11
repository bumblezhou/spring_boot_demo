package com.example.springboot.demo.controller;


import java.util.List;

// Importing required classes
// import javax.validation.Valid;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.demo.model.Department;
import com.example.springboot.demo.model.Message;
import com.example.springboot.demo.service.DepartmentService;
 
// Annotation
@RestController
// Class
public class RestDepartmentController {
 
    @Autowired private DepartmentService departmentService;
 
    // Save operation
    @PostMapping("/api/departments")
    public Department saveDepartment(@Valid @RequestBody Department department)
    {
        return departmentService.saveDepartment(department);
    }
 
    // Read operation
    @GetMapping("/api/departments")
    public List<Department> fetchDepartmentList()
    {
        return departmentService.fetchDepartmentList();
    }
 
    // Update operation
    @PutMapping("/api/departments/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId)
    {
        return departmentService.updateDepartment(
            department, departmentId);
    }
 
    // Delete operation
    @DeleteMapping("/api/departments/{id}")
    public Message deleteDepartmentById(@PathVariable("id")Long departmentId)
    {
        departmentService.deleteDepartmentById(departmentId);
        return new Message("Deleted Successfully");
    }
}
