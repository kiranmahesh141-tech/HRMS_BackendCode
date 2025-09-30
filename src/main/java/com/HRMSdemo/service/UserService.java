package com.HRMSdemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMSdemo.dto.Login;
import com.HRMSdemo.dto.Users;
import com.HRMSdemo.repository.LoginRepository;
import com.HRMSdemo.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository URepo;

    @Autowired
    private LoginRepository lRepo;

    public Users saveUser(Users user) {
       
        return URepo.save(user);
    }

    public Login findByIdentifier(String identifier) {
        return lRepo.findByUsernameOrEmail(identifier);
    }
}
