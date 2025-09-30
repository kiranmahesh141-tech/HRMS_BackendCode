package com.HRMSdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HRMSdemo.dto.Users;


public interface UserRepository extends JpaRepository<Users, String> {

	boolean existsByEmail(String email);

	boolean existsByPhone(Long phone);

	Users findByEmail(String email);

	@Query("SELECT u FROM Users u WHERE u.status = :status")
	List<Users> searchByStatus(@Param("status") String status);

	@Query("SELECT u FROM Users u WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Users> searchByname(@Param("name") String name);

	@Query("SELECT u FROM Users u WHERE str(u.phone) LIKE CONCAT('%', :phone, '%')")
	List<Users> searchByPhone(@Param("phone") String phone);
	
	@Query("SELECT u FROM Users u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))")
	List<Users> searchByEmail(@Param("email") String email);

}
