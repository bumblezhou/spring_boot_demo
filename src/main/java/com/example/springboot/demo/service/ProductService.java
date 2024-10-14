package com.example.springboot.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.springboot.demo.model.Product;

public interface ProductService {
    // Save operation
	Product save(Product product);

	// Read operation
	List<Product> fetchAll();

	// Search operation
	Page<Product> fetchItemsByPage(Pageable pageable);

	Page<Product> findItemsByPage(Set<Long> productTypeIds, Set<Long> supplierIds, String name, String specifications, String description, Pageable pageable);

	// Update operation
	Product update(Product product, Long Id);

	// Delete operation
	void delete(Long Id);
}
