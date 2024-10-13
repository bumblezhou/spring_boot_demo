package com.example.springboot.demo.model;

import jakarta.persistence.Column;
// deprecated
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// deprecated
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
    private Long Id;
    @Column(name = "NAME")
    private String Name;
    @Column(name = "ADDRESS")
    private String Address;
    @Column(name = "CODE")
    private String Code;
    @Column(name = "MEMBERS")
    private Long Members;
    @Column(name = "ISRUNNING")
    private Boolean IsRunning;
}