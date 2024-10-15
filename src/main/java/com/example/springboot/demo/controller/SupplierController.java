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

import com.example.springboot.demo.model.Message;
import com.example.springboot.demo.model.Supplier;
import com.example.springboot.demo.service.SupplierService;

import jakarta.validation.Valid;

@RestController
public class SupplierController {
    @Autowired private SupplierService supplierService;

    // Save operation
    @PostMapping("/api/suppliers")
    public Supplier saveDepartment(@Valid @RequestBody Supplier supplier)
    {
        return supplierService.save(supplier);
    }
 
    // Read operation
    @GetMapping("/api/suppliers")
    public List<Supplier> loadItems()
    {
        return supplierService.fetchAll();
    }

    /*
     * Example: http://localhost:8080/api/suppliers/fetchItemsByPage?page=0&size=10&sortBy=Id
     *
     * @RequestParam(defaultValue = "0") int page,
     * @RequestParam(defaultValue = "10") int size,
     * @RequestParam(defaultValue = "id") String sortBy
    */
    @GetMapping("/api/suppliers/fetchItemsByPage")
    public ResponseEntity<Page<Supplier>> fetchItemsByPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Supplier> suppliers = supplierService.fetchItemsByPage(pageable);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }
 
    // Update operation
    @PutMapping("/api/suppliers/{id}")
    public Supplier update(@RequestBody Supplier supplier, @PathVariable("id") Long Id)
    {
        return supplierService.update(supplier, Id);
    }
 
    // Delete operation
    @DeleteMapping("/api/suppliers/{id}")
    public Message delete(@PathVariable("id")Long Id)
    {
        supplierService.delete(Id);
        return new Message("Deleted Successfully");
    }
}
