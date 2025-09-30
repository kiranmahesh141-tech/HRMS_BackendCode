package com.HRMSdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.HRMSdemo.dto.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {


	@Query("SELECT u FROM Login u WHERE u.username = :login OR u.email = :login")
	Login findByUsernameOrEmail(@Param("login") String login);

    boolean existsByUsername(String username);


}
