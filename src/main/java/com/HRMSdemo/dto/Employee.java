package com.HRMSdemo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	private String empId;
	private String userName;
	private String DOB;
	private long phone;
	private String status;
	
}
