package com.example.springboot.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.springboot.demo.model.Supplier;

public interface SupplierService {
    // Save operation
    Supplier save(Supplier supplier);

    // Read operation
    List<Supplier> fetchAll();

    Page<Supplier> fetchItemsByPage(Pageable pageable);

    // Update operation
    Supplier update(Supplier supplier, Long Id);

    // Delete operation
    void delete(Long Id);
}
