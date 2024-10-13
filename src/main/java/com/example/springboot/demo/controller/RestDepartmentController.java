package com.example.springboot.demo.controller;


import java.util.List;

// Importing required classes
// import javax.validation.Valid;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    /*
     * Example: http://localhost:8080/api/departments/fetchDepartmentsByPage?page=0&size=10&sortBy=Id
     *
     * @RequestParam(defaultValue = "0") int page,
     * @RequestParam(defaultValue = "10") int size,
     * @RequestParam(defaultValue = "id") String sortBy
    */
    @GetMapping("/api/departments/fetchDepartmentsByPage")
    public ResponseEntity<Page<Department>> fetchDepartmentsByPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Department> departments = departmentService.fetchDepartmentsByPage(pageable);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    /*
     * Example: http://localhost:8080/api/departments/findDepartments?page=0&size=10&sortBy=Id&name=abc&address=xyz&code=123
     * 
     * @RequestParam(defaultValue = "0") int page,
     * @RequestParam(defaultValue = "10") int size,
     * @RequestParam(defaultValue = "id") String sortBy,
     * @RequestParam(required = false) String name,
     * @RequestParam(required = false) String address,
     * @RequestParam(required = false) String code
    */
    @GetMapping("/api/departments/findDepartments")
    public ResponseEntity<Page<Department>> findDepartments(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String address,
        @RequestParam(required = false) String code
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Department> departments = departmentService.findDepartments(name, address, code, pageable);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
 
    // Update operation
    @PutMapping("/api/departments/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Long Id)
    {
        return departmentService.updateDepartment(department, Id);
    }
 
    // Delete operation
    @DeleteMapping("/api/departments/{id}")
    public Message deleteDepartmentById(@PathVariable("id")Long Id)
    {
        departmentService.deleteDepartmentById(Id);
        return new Message("Deleted Successfully");
    }
}
