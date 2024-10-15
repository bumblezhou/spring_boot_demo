package com.example.springboot.demo.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.demo.model.Product;
import com.example.springboot.demo.model.Message;
import com.example.springboot.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
    @Autowired private ProductService productService;
 
    // Save operation
    @PostMapping("/api/products")
    public Product saveDepartment(@Valid @RequestBody Product product)
    {
        return productService.save(product);
    }
 
    // Read operation
    /*
     * Example: http://localhost:8080/api/products/fetchItemsByPage?page=0&size=10&sortBy=Id
     *
     * @RequestParam(defaultValue = "0") int page,
     * @RequestParam(defaultValue = "10") int size,
     * @RequestParam(defaultValue = "id") String sortBy
    */
    @GetMapping("/api/products/fetchItemsByPage")
    public ResponseEntity<Page<Product>> fetchItemsByPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> products = productService.fetchItemsByPage(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /*
     * Example: http://localhost:8080/api/products/findProducts?page=0&size=10&sortBy=id&productTypeIds=1,2,3&supplierIds=4,5,6&name=ProductName&specifications=Specs&priceMin=1&priceMax=10
     *
     * @RequestParam(defaultValue = "0") int page,
     * @RequestParam(defaultValue = "10") int size,
     * @RequestParam(defaultValue = "id") String sortBy,
     * @RequestParam(required = false) String productTypeIds,
     * @RequestParam(required = false) String supplierIds,
     * @RequestParam(required = false) String name,
     * @RequestParam(required = false) String specifications,
     * @RequestParam(required = false) String priceMin,
     * @RequestParam(required = false) String priceMax
    */
    @GetMapping("/api/products/findProducts")
    public ResponseEntity<Page<Product>> findProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(required = false) String productTypeIds,
        @RequestParam(required = false) String supplierIds,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String specifications,
        @RequestParam(required = false) String priceMin,
        @RequestParam(required = false) String priceMax
    ) {
        // Handle null or empty values for productTypeIds and supplierIds
        Set<Long> productTypeIdSet = null;
        Set<Long> supplierIdSet = null;

        if (productTypeIds != null && !productTypeIds.isEmpty()) {
            String[] productTypeIdsArray = productTypeIds.split(",");
            productTypeIdSet = new HashSet<Long>(productTypeIdsArray.length);
            for (int i = 0; i < productTypeIdsArray.length; i++) {
                productTypeIdSet.add(Long.parseLong(productTypeIdsArray[i]));
            }
        }

        if (supplierIds != null && !supplierIds.isEmpty()) {
            String[] supplierIdsArray = supplierIds.split(",");
            supplierIdSet = new HashSet<Long>(supplierIdsArray.length);
            for (int i = 0; i < supplierIdsArray.length; i++) {
                supplierIdSet.add(Long.parseLong(supplierIdsArray[i]));
            }
        }

        Double priceMaxValue = 0.0;
        Double priceMinValue = 0.0;
        if (priceMax != null && !priceMax.isEmpty()) {
            priceMaxValue = Double.parseDouble(priceMax);
            priceMinValue = Double.parseDouble(priceMin);
        }

        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> products = productService.findItemsByPage(productTypeIdSet, supplierIdSet, name, specifications, priceMinValue, priceMaxValue, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
 
    // Update operation
    @PutMapping("/api/products/{id}")
    public Product update(@RequestBody Product product, @PathVariable("id") Long Id)
    {
        return productService.update(product, Id);
    }
 
    // Delete operation
    @DeleteMapping("/api/products/{id}")
    public Message delete(@PathVariable("id")Long Id)
    {
        productService.delete(Id);
        return new Message("Deleted Successfully");
    }
}
