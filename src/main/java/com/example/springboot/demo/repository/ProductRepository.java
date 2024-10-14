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
        "(:#{#productTypeIds == null} = true or (P.productTypeId in (:productTypeIds))) OR" +
        "(:#{#supplierIds == null} = true or (P.supplierId in (:supplierIds))) OR" +
        "(P.name LIKE %:name%) OR " +
        "(P.specifications LIKE %:specifications%) OR " +
        "(P.description LIKE %:description%)")
  Page<Product> findByFilters(
    @Param("productTypeIds") Set<Long> productTypeIds,
    @Param("supplierIds") Set<Long> supplierIds,
    @Param("name") String name, 
    @Param("specifications") String specifications, 
    @Param("description") String description, 
    Pageable pageable);
}
