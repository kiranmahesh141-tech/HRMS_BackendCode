package com.HRMSdemo.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.HRMSdemo.validation.Validations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Users {
	@Id
	private String Iid="USR" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

	@NotBlank
	@Size(min = 3)
	private String firstName;
	
	@NotBlank
	@Size(min = 3)
	private String lastName;
	
	@Email
	@Column(unique = true, nullable = false)
	private String email;

	@Column(unique = true, nullable = false)
	private long phone;

	private LocalDate DOB;
	
	
	private String fullName;
	private String status="ONHOLD";
	
	@Transient // not persisted
    private Validations validation;

	public void setFirstName(String firstName) {
        if (validation != null) {
            this.firstName = validation.formatFirstName(firstName);
        } else {
            this.firstName = firstName;
        }
    }
	
	@PrePersist
	@PreUpdate
	public void preSave() {
	    this.fullName = firstName + " " + lastName;
	}
	@OneToOne(mappedBy = "users")
	private Login login; 

}
