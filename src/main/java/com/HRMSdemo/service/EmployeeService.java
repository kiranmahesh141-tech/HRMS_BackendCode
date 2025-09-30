package com.HRMSdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMSdemo.dto.Employee;
import com.HRMSdemo.dto.Login;
import com.HRMSdemo.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository empRepository;
    
    public Employee saveEmployee(Employee emp){
       
        
        
        return empRepository.save(emp);
    }
}
