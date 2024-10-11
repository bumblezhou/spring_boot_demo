package com.example.springboot.demo.model;

import jakarta.persistence.Column;
// Importing required classes
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
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
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long departmentId;
    @Column(name = "NAME")
    private String departmentName;
    @Column(name = "ADDRESS")
    private String departmentAddress;
    @Column(name = "CODE")
    private String departmentCode;
    @Column(name = "MEMBERS")
    private Long departmentMembers;
    @Column(name = "ISRUNNING")
    private Boolean departmentIsRunning;
}