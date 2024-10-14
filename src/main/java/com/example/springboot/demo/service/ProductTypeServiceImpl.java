package com.example.springboot.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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

    // Update operation
    @Override
    public ProductType save(ProductType procurementType, Long Id) {

        ProductType depDB = procurementTypeRepository.findById(Id).get();

        if (Objects.nonNull(procurementType.getName()) && !"".equalsIgnoreCase(procurementType.getName())) {
            depDB.setName(procurementType.getName());
        }

        if (Objects.nonNull(procurementType.getDescription()) && !"".equalsIgnoreCase(procurementType.getDescription())) {
            depDB.setDescription(procurementType.getDescription());
        }

        return procurementTypeRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void delete(Long Id) {
        procurementTypeRepository.deleteById(Id);
    }
}
