package com.example.springboot.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.demo.model.Supplier;
import com.example.springboot.demo.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    // Save operation
    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Read operation
    @Override
    public List<Supplier> fetchAll() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    @Override
    public Page<Supplier> fetchItemsByPage(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    // Update operation
    @Override
    public Supplier update(Supplier supplier, Long Id) {

        Supplier depDB = supplierRepository.findById(Id).get();

        if (Objects.nonNull(supplier.getName()) && !"".equalsIgnoreCase(supplier.getName())) {
            depDB.setName(supplier.getName());
        }

        if (Objects.nonNull(supplier.getAddress()) && !"".equalsIgnoreCase(supplier.getAddress())) {
            depDB.setAddress(supplier.getAddress());
        }

        if (Objects.nonNull(supplier.getEmail()) && !"".equalsIgnoreCase(supplier.getEmail())) {
            depDB.setEmail(supplier.getEmail());
        }

        if (Objects.nonNull(supplier.getPhone()) && !"".equalsIgnoreCase(supplier.getPhone())) {
            depDB.setPhone(supplier.getPhone());
        }

        if (Objects.nonNull(supplier.getWebsite()) && !"".equalsIgnoreCase(supplier.getWebsite())) {
            depDB.setWebsite(supplier.getWebsite());
        }

        if (Objects.nonNull(supplier.getCreatedDate())) {
            depDB.setCreatedDate(supplier.getCreatedDate());
        }

        if (Objects.nonNull(supplier.getDescription()) && !"".equalsIgnoreCase(supplier.getDescription())) {
            depDB.setDescription(supplier.getDescription());
        }

        return supplierRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void delete(Long Id) {
        supplierRepository.deleteById(Id);
    }
}
