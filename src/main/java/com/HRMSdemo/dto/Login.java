package com.HRMSdemo.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "login")  // maps to your existing login table
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = true, unique = true)
    private String email; 

    @Column(nullable = false)
    private String password;  

    private String role;   
    
    private String status;
    @OneToOne
    @JoinColumn(name = "uid",referencedColumnName = "iId")
    private Users users;
}
