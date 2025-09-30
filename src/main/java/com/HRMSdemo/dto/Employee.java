package com.HRMSdemo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.HRMSdemo.validation.FirstNameValidation;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    private String empId;

    @Size(min = 3)
    private String firstName;

    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String fullName;

    private String position;
    private String department;
    private String role;
    private Double salary;
    private LocalDate hireDate;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Transient // not persisted
    private FirstNameValidation validation;

    // Pass validation class (Spring-managed) from service/controller
    public void setValidation(FirstNameValidation validation) {
        this.validation = validation;
    }

    public void setFirstName(String firstName) {
        if (validation != null) {
            this.firstName = validation.formatFirstName(firstName);
        } else {
            this.firstName = firstName;
        }
    }

    @PrePersist
    public void generateEmpId() {
        if (validation != null && empId == null) {
            empId = validation.generateUniqueEmpId(firstName); 
        }
        preSave();
    }

    @PreUpdate
    public void updateFullName() {
        preSave();
    }

    private void preSave() {
        fullName = firstName + " " + lastName;
    }
}
