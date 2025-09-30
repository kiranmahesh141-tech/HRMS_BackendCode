package com.HRMSdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.HRMSdemo.dto.Users;


public interface UserRepository extends JpaRepository<Users, String> {

	 boolean existsByEmail(String email);

    boolean existsByPhone(Long phone);


    List<Users> findByStatus(String status);

    List<Users> findByFullName(String fullName);

    List<Users> findByPhone(Long phone);

    List<Users> findByEmail(String email);
}
