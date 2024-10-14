package com.example.springboot.demo.service;

import com.example.springboot.demo.model.Department;
// Importing required classes
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Class
public interface DepartmentService {

	// Save operation
	Department saveDepartment(Department department);

	// Read operation
	List<Department> fetchDepartmentList();

	// Search operation
	Page<Department> fetchDepartmentsByPage(Pageable pageable);

	Page<Department> findDepartments(String name, String address, String code, Pageable pageable);

	// Update operation
	Department updateDepartment(Department department, Long Id);

	// Delete operation
	void deleteDepartmentById(Long Id);
}
