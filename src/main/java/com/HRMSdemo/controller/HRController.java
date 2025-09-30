package com.HRMSdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HRMSdemo.dto.Users;
import com.HRMSdemo.service.HRService;
@RestController
@RequestMapping("/hr")
@CrossOrigin(origins = "http://localhost:3000")
public class HRController {
    @Autowired
    private HRService hrService;

    @PutMapping("/update-status")
	public ResponseEntity<Users> updateStatus(
	    @RequestParam(value = "id", required = false) String id,
	    @RequestParam(value = "name", required = false) String name,
	    @RequestParam(value = "email", required = false) String email,
	    @RequestParam(value = "phone", required = false) String phone,
	    @RequestParam("status") String status,
	    @RequestParam(value ="role",required = false) String role) {
	    Users updatedUser = hrService.updateStatusByIdentifier(id, name, email, phone, status, role);
	    return ResponseEntity.ok(updatedUser);
	}
    @GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>> getall() {
		return new ResponseEntity<>(hrService.getAll(), HttpStatus.OK);
	}


	@GetMapping("/searchName")
	public ResponseEntity<List<Users>> searchUsersByName(@RequestParam("fullName") String name) {
		List<Users> users = hrService.searchName(name);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/searchPhone")
	public ResponseEntity<List<Users>> searchUsersByPhone(
	    @RequestParam("phone") String phone
	) {
	    List<Users> users = hrService.searchPhone(phone);
	    return ResponseEntity.ok(users);
	}

	@GetMapping("/searchEmail")
	public ResponseEntity<List<Users>> searchUsersByEmail(@RequestParam("email") String email) {
		List<Users> users = hrService.searchEmail(email);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

    @GetMapping("/searchStatus")
    public ResponseEntity<List<Users>> searchByStatus(@RequestParam("status") String status){
        List<Users> users = hrService.searchByStatus(status);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
