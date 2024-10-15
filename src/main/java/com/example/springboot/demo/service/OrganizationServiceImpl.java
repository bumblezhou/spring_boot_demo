package com.example.springboot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.demo.model.Organization;
import com.example.springboot.demo.repository.OrganizationRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public List<Organization> loadTree() {
        // Load all organizations in one query
        List<Organization> allOrganizations = organizationRepository.findAll();
        
        // Map to store organizations by their IDs for easy lookup
        Map<Long, Organization> organizationMap = allOrganizations.stream()
            .collect(Collectors.toMap(Organization::getId, org -> org));

        // List to store the root nodes of the organization tree
        List<Organization> rootOrganizations = new ArrayList<>();

        // Build the tree structure by assigning children to their parents
        for (Organization org : allOrganizations) {
            if (org.getParentId() == null) {
                // If no parent, it's a root node
                rootOrganizations.add(org);
            } else {
                // Get the parent organization from the map and add the current org as a child
                Organization parentOrg = organizationMap.get(org.getParentId());
                if (parentOrg != null) {
                    if (parentOrg.getChildren() == null) {
                        parentOrg.setChildren(new ArrayList<>());
                    }
                    parentOrg.getChildren().add(org);
                }
            }
        }

        return rootOrganizations; // Return the root organizations, which include all the sub-children
    }

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Optional<Organization> getOrganizationById(Long id) {
        return organizationRepository.findById(id);
    }

    @Override
    public Optional<Organization> updateOrganization(Long id, Organization organizationDetails) {
        return organizationRepository.findById(id)
            .map(existingOrg -> {
                existingOrg.setName(organizationDetails.getName());
                existingOrg.setAddress(organizationDetails.getAddress());
                existingOrg.setDescription(organizationDetails.getDescription());
                existingOrg.setParentId(organizationDetails.getParentId());
                return organizationRepository.save(existingOrg);
            });
    }

    @Override
    public boolean deleteOrganization(Long id) {
        return organizationRepository.findById(id)
                .map(existingOrg -> {
                    organizationRepository.delete(existingOrg);
                    return true;
                })
                .orElse(false);
    }
}
