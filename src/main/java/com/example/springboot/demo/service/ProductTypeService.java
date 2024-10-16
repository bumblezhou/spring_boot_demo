package com.example.springboot.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.springboot.demo.model.ProductType;

public interface ProductTypeService {
    // Save operation
    ProductType save(ProductType productType);

    // Read operation
    List<ProductType> fetchAll();

    Page<ProductType> fetchItemsByPage(Pageable pageable);

    // Update operation
    ProductType update(ProductType productType, Long Id);

    // Delete operation
    void delete(Long Id);
}
