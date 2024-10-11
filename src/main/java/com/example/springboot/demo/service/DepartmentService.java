package com.example.springboot.demo.service;

import com.example.springboot.demo.model.Department;
// Importing required classes
import java.util.List;

// Class
public interface DepartmentService {

	// Save operation
	Department saveDepartment(Department department);

	// Read operation
	List<Department> fetchDepartmentList();

	// Update operation
	Department updateDepartment(Department department, Long departmentId);

	// Delete operation
	void deleteDepartmentById(Long departmentId);
}