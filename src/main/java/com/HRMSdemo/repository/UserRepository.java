package com.HRMSdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.HRMSdemo.dto.Users;


public interface UserRepository extends JpaRepository<Users, String> {

	boolean existsByEmail(String email);

	boolean existsByPhone(Long phone);

	Users findByEmail(String email);

	
	List<Users> searchByStatus(String status);

	
	List<Users> searchByname(String name);

	
	List<Users> searchByPhone(String phone);
	
	
	List<Users> searchByEmail(String email);

}
