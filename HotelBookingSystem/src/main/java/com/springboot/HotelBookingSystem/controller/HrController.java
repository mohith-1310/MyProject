package com.springboot.HotelBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.enumerate.Role;
import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.Hr;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.ExecutiveService;
import com.springboot.HotelBookingSystem.service.HrService;
import com.springboot.HotelBookingSystem.service.UserService;





@RestController
@RequestMapping("/hr")
@CrossOrigin(origins = {"http://localhost:3000"})

public class HrController {

	@Autowired
	private HrService hrService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ExecutiveService executiveService;

	@PostMapping("/add")
	public Hr insertHr(@RequestBody Hr hr) {

		// Getting user details from postman
		User user = hr.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(Role.HR);
		
		// inserting hr details in user table
		user = userService.insert(user);
		hr.setUser(user);
		
		
		return hrService.insertHr(hr);
	}
	
	@GetMapping("/getallexecutives")
	public List<Executive> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size)
	{
		Pageable pageable = PageRequest.of(page,size);
		return executiveService.getAll(pageable);
		
		
		
		}
}