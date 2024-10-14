package com.example.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.demo.model.Supplier;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
}
