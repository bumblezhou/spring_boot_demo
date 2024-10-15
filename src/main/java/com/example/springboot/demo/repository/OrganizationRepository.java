package com.example.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.demo.model.Organization;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    // Find all organizations with a given parentId
    List<Organization> findByParentId(Long parentId);
}
