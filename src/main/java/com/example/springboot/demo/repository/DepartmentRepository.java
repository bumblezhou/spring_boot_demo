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

  @Query("SELECT new com.example.springboot.demo.model.Department(D.Id, D.Name, D.Address, D.Code, D.Members, D.IsRunning) FROM Department D WHERE " +
        "(:#{#name == null} = true or D.Name LIKE %:name%) AND " +
        "(:#{#address == null} = true or D.Address LIKE %:address%) AND " +
        "(:#{#code == null} = true or D.Code LIKE %:code%) AND " +
        "(:#{#membersMin == 0} = true or (D.Members >= (:membersMin))) AND" +
        "(:#{#membersMax == 0} = true or (D.Members <= (:membersMax)))")
  Page<Department> findByFilters(
    @Param("name") String name, 
    @Param("address") String address, 
    @Param("code") String code, 
    @Param("membersMin") Integer membersMin, 
    @Param("membersMax") Integer membersMax, 
    Pageable pageable);
}
