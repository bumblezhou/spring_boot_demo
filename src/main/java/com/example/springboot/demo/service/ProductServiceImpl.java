package com.example.springboot.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.demo.model.Product;
import com.example.springboot.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> fetchAll() {
        return (List<Product>)productRepository.findAll();
    }

    @Override
    public Page<Product> fetchItemsByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findItemsByPage(Set<Long> productTypeIds, Set<Long> supplierIds, String name, String specifications, Double priceMin, Double priceMax, Pageable pageable) {
        return productRepository.findByFilters(productTypeIds, supplierIds, name, specifications, priceMin, priceMax, pageable);
    }

    @Override
    public Product update(Product product, Long Id) {
        Product depDB = productRepository.findById(Id).get();

        if (Objects.nonNull(product.getName()) && !"".equalsIgnoreCase(product.getName())) {
            depDB.setName(product.getName());
        }

        if (Objects.nonNull(product.getDescription()) && !"".equalsIgnoreCase(product.getDescription())) {
            depDB.setDescription(product.getDescription());
        }

        if (Objects.nonNull(product.getSpecifications()) && !"".equalsIgnoreCase(product.getSpecifications())) {
            depDB.setDescription(product.getSpecifications());
        }

        if (Objects.nonNull(product.getDetailsUrl()) && !"".equalsIgnoreCase(product.getDetailsUrl())) {
            depDB.setDetailsUrl(product.getDetailsUrl());
        }

        if (Objects.nonNull(product.getImageUrl()) && !"".equalsIgnoreCase(product.getImageUrl())) {
            depDB.setImageUrl(product.getImageUrl());
        }

        if (Objects.nonNull(product.getProductTypeId())) {
            depDB.setProductTypeId(product.getProductTypeId());
        }

        if (Objects.nonNull(product.getSupplierId())) {
            depDB.setSupplierId(product.getSupplierId());
        }

        if (Objects.nonNull(product.getPrice())) {
            depDB.setPrice(product.getPrice());
        }

        return productRepository.save(depDB);
    }

    @Override
    public void delete(Long Id) {
        productRepository.deleteById(Id);
    }
    
}
