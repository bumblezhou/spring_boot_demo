package com.example.springboot.demo.controller;

import java.util.List;

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

import com.example.springboot.demo.model.ProductType;
import com.example.springboot.demo.model.Message;
import com.example.springboot.demo.service.ProductTypeService;

import jakarta.validation.Valid;

@RestController
public class ProductTypeController {
    @Autowired private ProductTypeService productTypeService;

    // Save operation
    @PostMapping("/api/product_types")
    public ProductType saveDepartment(@Valid @RequestBody ProductType productType)
    {
        return productTypeService.save(productType);
    }
 
    // Read operation
    @GetMapping("/api/product_types")
    public List<ProductType> loadItems()
    {
        return productTypeService.fetchAll();
    }

    /*
     * Example: http://localhost:8080/api/product_types/fetchItemsByPage?page=0&size=10&sortBy=Id
     *
     * @RequestParam(defaultValue = "0") int page,
     * @RequestParam(defaultValue = "10") int size,
     * @RequestParam(defaultValue = "id") String sortBy
    */
    @GetMapping("/api/product_types/fetchItemsByPage")
    public ResponseEntity<Page<ProductType>> fetchItemsByPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<ProductType> product_types = productTypeService.fetchItemsByPage(pageable);
        return new ResponseEntity<>(product_types, HttpStatus.OK);
    }
 
    // Update operation
    @PutMapping("/api/product_types/{id}")
    public ProductType update(@RequestBody ProductType productType, @PathVariable("id") Long Id)
    {
        return productTypeService.update(productType, Id);
    }
 
    // Delete operation
    @DeleteMapping("/api/product_types/{id}")
    public Message delete(@PathVariable("id")Long Id)
    {
        productTypeService.delete(Id);
        return new Message("Deleted Successfully");
    }
}
