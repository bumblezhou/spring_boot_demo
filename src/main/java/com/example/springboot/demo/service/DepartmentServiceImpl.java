package com.example.springboot.demo.service;

import com.example.springboot.demo.model.Department;
import com.example.springboot.demo.repository.DepartmentRepository;
// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Annotation
@Service
// Class implementing DepartmentService class
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Save operation
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Read operation
    @Override
    public List<Department> fetchDepartmentList() {
        return (List<Department>) departmentRepository.findAll();
    }

    public Page<Department> fetchDepartmentsByPage(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    public Page<Department> findDepartments(String name, String address, String code, Pageable pageable) {
        return departmentRepository.findByFilters(name, address, code, pageable);
    }

    // Update operation
    @Override
    public Department updateDepartment(Department department, Long Id) {

        Department depDB = departmentRepository.findById(Id).get();

        if (Objects.nonNull(department.getName()) && !"".equalsIgnoreCase(department.getName())) {
            depDB.setName(department.getName());
        }

        if (Objects.nonNull(department.getAddress()) && !"".equalsIgnoreCase(department.getAddress())) {
            depDB.setAddress(department.getAddress());
        }

        if (Objects.nonNull(department.getCode()) && !"".equalsIgnoreCase(department.getCode())) {
            depDB.setCode(department.getCode());
        }

        if (Objects.nonNull(department.getMembers())) {
            depDB.setMembers(department.getMembers());
        }

        if (Objects.nonNull(department.getIsRunning())) {
            depDB.setIsRunning(department.getIsRunning());
        }

        return departmentRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void deleteDepartmentById(Long Id) {
        departmentRepository.deleteById(Id);
    }
}
