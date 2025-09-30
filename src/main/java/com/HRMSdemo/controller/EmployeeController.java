package com.HRMSdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HRMSdemo.dto.Employee;
import com.HRMSdemo.dto.Login;
import com.HRMSdemo.repository.EmployeeRepository;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeRepository eRepository;
    Login login=null;
    Employee emp = new Employee();
    // public Employee savEmployee(Employee emp){
    //     emp.setEmail(null);
    // }

}
