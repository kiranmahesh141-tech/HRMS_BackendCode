package com.HRMSdemo.validation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HRMSdemo.repository.EmployeeRepository;
import com.HRMSdemo.repository.LoginRepository;

@Component
public class Validations {

    @Autowired
    private EmployeeRepository empRepository;
    private LoginRepository lRepository;

    // Format first name: replace spaces with "_"
    public String formatFirstName(String firstName) {
        if (firstName != null) {
            firstName = firstName.trim().replaceAll("\\s+", "_");
        }
        return firstName;
    }

    // Generate unique empId recursively
    public String generateUniqueEmpId(String firstName) {
        String namePart = firstName.substring(0, 3).toUpperCase();
        String randomPart = UUID.randomUUID().toString().substring(0, 3).toUpperCase();
        String empId = "EMP" + namePart + randomPart;

        if (empRepository.existsById(empId)) {
            return generateUniqueEmpId(firstName);
        }

        return empId;
    }
    public String generateUserName(String userName){
        String namePart = userName.substring(0, 3).toUpperCase();
        String randomPart = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        String uName=namePart+randomPart;

        if(lRepository.existsByUsername(uName)){
            return generateUserName(userName);
        }
        return uName;
    }

}
