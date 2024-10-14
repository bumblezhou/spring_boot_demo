package com.example.springboot.demo.service;

import java.util.List;

import com.example.springboot.demo.model.ProductType;

public interface ProductTypeService {
    // Save operation
    ProductType save(ProductType productType);

    // Read operation
    List<ProductType> fetchAll();

    // Update operation
    ProductType save(ProductType department, Long Id);

    // Delete operation
    void delete(Long Id);
}
