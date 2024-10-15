package com.example.springboot.demo.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springboot.demo.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.example.springboot.demo.model.Product(P.id, P.productTypeId, P.supplierId, P.name, P.price, P.imageUrl, P.specifications, P.detailsUrl, P.description) FROM Product P WHERE " +
        "(:#{#productTypeIds == null} = true or (P.productTypeId in (:productTypeIds))) AND" +
        "(:#{#supplierIds == null} = true or (P.supplierId in (:supplierIds))) AND" +
        "(:#{#name == null} = true or P.name LIKE %:name%) AND " +
        "(:#{#specifications == null} = true or P.specifications LIKE %:specifications%) AND " +
        "(:#{#priceMin == 0.0} = true or (P.price >= (:priceMin))) AND" +
        "(:#{#priceMax == 0.0} = true or (P.price <= (:priceMax)))")
  Page<Product> findByFilters(
    @Param("productTypeIds") Set<Long> productTypeIds,
    @Param("supplierIds") Set<Long> supplierIds,
    @Param("name") String name, 
    @Param("specifications") String specifications, 
    @Param("priceMin") Double priceMin, 
    @Param("priceMax") Double priceMax, 
    Pageable pageable);
}
