package com.HRMSdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HRMSdemo.dto.Users;


public interface UserRepository extends JpaRepository<Users, String> {

	boolean existsByEmail(String email);

	boolean existsByPhone(Long phone);

	

	
	List<Users> findByStatus(String status);

	
	@Query("SELECT u FROM Users u WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Users> findByFullName(@Param("name") String name);

	@Query("SELECT u FROM Users u WHERE str(u.phone) LIKE CONCAT('%', :phone, '%')")
	List<Users> findByPhone(@Param("phone") String phone);
	
	@Query("SELECT u FROM Users u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))")
	List<Users> findByEmail(@Param("email") String email);

}
