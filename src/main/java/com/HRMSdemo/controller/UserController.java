package com.HRMSdemo.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HRMSdemo.Exceptions.InValidPasswordException;
import com.HRMSdemo.Exceptions.UserNotActiveException;
import com.HRMSdemo.Exceptions.UserNotFoundException;
import com.HRMSdemo.dto.Login;
import com.HRMSdemo.dto.LoginRequest;
import com.HRMSdemo.dto.Users;
import com.HRMSdemo.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> saveUser(@Valid @RequestBody Users user) {
		Users savedUser = service.saveUser(user); // persist and get saved user

		Map<String, Object> response = new HashMap<>();
		response.put("status", "success");
		response.put("message", "User registered successfully");
		response.put("id", savedUser.getIid());
		response.put("firstName", savedUser.getFirstName());
		response.put("lastName", savedUser.getLastName());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/update-status")
	public ResponseEntity<Users> updateStatus(
	    @RequestParam(value = "id", required = false) String id,
	    @RequestParam(value = "name", required = false) String name,
	    @RequestParam(value = "email", required = false) String email,
	    @RequestParam(value = "phone", required = false) String phone,
	    @RequestParam("status") String status,
	    @RequestParam(value ="role",required = false) String role) {
	    Users updatedUser = service.updateStatusByIdentifier(id, name, email, phone, status, role);
	    return ResponseEntity.ok(updatedUser);
	}


	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>> getall() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}

	@GetMapping("/getByEmail/{email}")
	public Users getUserbyEmail(@PathVariable String email) {
		return service.getUserByEmail(email);
	}

	@GetMapping("/searchName")
	public ResponseEntity<List<Users>> searchUsersByName(@RequestParam("fullName") String name) {
		List<Users> users = service.searchName(name);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/searchPhone")
	public ResponseEntity<List<Users>> searchUsersByPhone(
	    @RequestParam("phone") String phone
	) {
	    List<Users> users = service.searchPhone(phone);
	    return ResponseEntity.ok(users);
	}


	@GetMapping("/searchEmail")
	public ResponseEntity<List<Users>> searchUsersByEmail(@RequestParam("email") String email) {
		List<Users> users = service.searchEmail(email);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
	    Login login = service.findByIdentifier(request.getIdentifier());
	    
	 
	    if (login == null) {
	        throw new UserNotFoundException("User not found");
	    }
	    
	    if(!"active".equalsIgnoreCase(login.getStatus())) {
	    	throw new UserNotActiveException("Employee not Active");
	    }
	    
	    if (!login.getPassword().equals(request.getPassword())) {
	        throw new InValidPasswordException("Invalid password");
	    }
	 
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("role", login.getRole());
	    response.put("id", login.getId());
	    response.put("username", login.getUsername());
	 
	    return ResponseEntity.ok(response);
	}

	

}
