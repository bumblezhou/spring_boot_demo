package com.example.springboot.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.springboot.demo.model.Organization;

public interface OrganizationService {

    // Load full organizational tree
    List<Organization> loadTree();

    // Create a new organization
    Organization createOrganization(Organization organization);

    // Get all organizations
    List<Organization> getAllOrganizations();

    // Get an organization by ID
    Optional<Organization> getOrganizationById(Long id);

    // Update an existing organization
    Optional<Organization> updateOrganization(Long id, Organization organizationDetails);

    // Delete an organization
    boolean deleteOrganization(Long id);
}