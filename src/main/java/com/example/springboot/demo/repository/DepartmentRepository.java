package com.example.springboot.demo.repository;

import com.example.springboot.demo.model.Department;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

  @Query("SELECT D.departmentId, D.departmentName, D.departmentAddress, D.departmentCode, D.departmentMembers, D.departmentIsRunning FROM Department D WHERE " +
        "(D.departmentName LIKE %:name%) OR " +
        "(D.departmentAddress LIKE %:address%) OR " +
        "(D.departmentCode LIKE %:code%)")
  Page<Department> findByFilters(
    @Param("name") String name, 
    @Param("address") String address, 
    @Param("code") String code, 
    Pageable pageable);
}
