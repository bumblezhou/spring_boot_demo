package com.example.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.demo.model.ProcurementType;


@Repository
public interface ProcurementTypeRepository extends JpaRepository<ProcurementType, Long> {
    
}
