package com.example.springboot.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.demo.model.ProductType;
import com.example.springboot.demo.repository.ProductTypeRepository;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository procurementTypeRepository;

    // Save operation
    @Override
    public ProductType save(ProductType productType) {
        return procurementTypeRepository.save(productType);
    }

    // Read operation
    @Override
    public List<ProductType> fetchAll() {
        return (List<ProductType>) procurementTypeRepository.findAll();
    }

    @Override
    public Page<ProductType> fetchItemsByPage(Pageable pageable) {
        return procurementTypeRepository.findAll(pageable);
    }

    // Update operation
    @Override
    public ProductType update(ProductType productType, Long Id) {

        ProductType depDB = procurementTypeRepository.findById(Id).get();

        if (Objects.nonNull(productType.getName()) && !"".equalsIgnoreCase(productType.getName())) {
            depDB.setName(productType.getName());
        }

        if (Objects.nonNull(productType.getDescription()) && !"".equalsIgnoreCase(productType.getDescription())) {
            depDB.setDescription(productType.getDescription());
        }

        return procurementTypeRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void delete(Long Id) {
        procurementTypeRepository.deleteById(Id);
    }
}
