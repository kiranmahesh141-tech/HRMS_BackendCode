package com.HRMSdemo.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMSdemo.Exceptions.UserNotFoundException;
import com.HRMSdemo.dto.Employee;
import com.HRMSdemo.dto.Login;
import com.HRMSdemo.dto.Users;
import com.HRMSdemo.repository.LoginRepository;
import com.HRMSdemo.repository.UserRepository;

@Service
public class HRService {
    @Autowired
    private UserRepository URepo;

    @Autowired
    private LoginRepository lRepo;

    @Autowired
    private EmailService emailService;

     public Users updateStatusByIdentifier(String iId, String name, String mail, String phone,
                                          String status, String role) {

        Users user = null;

        // Search by ID
        if (iId != null && !iId.isEmpty()) {
            user = URepo.findById(iId).orElse(null);
        }

        // Search by name
        if (user == null && name != null && !name.isEmpty()) {
            List<Users> users = URepo.findByFullName(name);
            if (!users.isEmpty()) user = users.get(0);
        }

        // Search by email
        if (user == null && mail != null && !mail.isEmpty()) {
             List<Users> users = URepo.findByEmail(mail);
            if (!users.isEmpty()) user = users.get(0);
        }

        // Search by phone
        if (user == null && phone !=null && !phone.isEmpty()) {
            List<Users> users = URepo.findByPhone(phone);
            if (!users.isEmpty()) user = users.get(0);
        }

        if (user == null) throw new UserNotFoundException("User Not Found");

        // Update status
        user.setStatus(status);
        URepo.save(user);

        // Generate login
        Login login = new Login();
        login.setEmail(user.getEmail());

        // Generate one password for DB and email
        login.setPassword(RandomStringUtils.random(10, 33, 126, true, true));
        login.setStatus("ACTIVE");
        login.setRole(status.equalsIgnoreCase("selected") ? role : null);

        // Set username and save once
        
        lRepo.save(login);

        //generate Employee details
         Employee emp1= new Employee();
        if (login!=null) {
            emp1.setEmail(login.getEmail());
            emp1.setRole(login.getRole());
            emp1.setStatus(login.getStatus());
        }
        if(user!=null){
            emp1.setFirstName(user.getFirstName());
            emp1.setLastName(user.getLastName());
            emp1.setPhone(user.getPhone());
        }

        // Send email
        if (role != null && !role.isEmpty()) {
        emailService.sendHtmlEmailWithImage(
                login.getEmail(),
                "Welcome to PharmCrux - Onboarding Details",
                user.getFirstName(),
                role,
                login.getUsername(),
                login.getPassword());
        }
        return user;
    }

    public List<Users> getAll() {
        return URepo.findAll();
    }

    public List<Users> searchName(String name) {
        return URepo.findByFullName(name);
    }

    public List<Users> searchPhone(String phone) {
        return URepo.findByPhone(phone);
    }
    
    public List<Users> searchEmail(String email) {
        return URepo.findByEmail(email);
    }

    public List<Users> searchByStatus(String status){
        return URepo.findByStatus(status);
    }
}
