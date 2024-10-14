package com.example.springboot.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_TYPE_ID")
    private Long productTypeId;

    @Column(name = "SUPPLIER_ID")
    private Long supplierId;
    
    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "SPECIFICATIONS")
    private String specifications;

    @Column(name = "DETAILS_URL")
    private String detailsUrl;

    @Column(name = "DESCRIPTION")
    private String description;
}
