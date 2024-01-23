package com.springboot.HotelBookingSystem.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.enumerate.Role;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.service.UserService;

import com.springboot.HotelBookingSystem.dto.Customerdto;

@RestController
@Configuration
@RequestMapping("/feelhome")
@CrossOrigin(origins = {"http://localhost:3000"})
	public class CustomerController {
		@Autowired
		private CustomerService customerService;
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@PostMapping("/customer/add")
		public Customer insertCustomer(@RequestBody Customer customer) {
			/* step1 get user information*/
			User user = customer.getUser();
			/*Step2 get user password and attach to variable*/
			String plainPassword = user.getPassword();
			/*Step3 encode the user password*/
			String encodedPassword = passwordEncoder.encode(plainPassword);
			/*Step4 set encoded password to user*/
			user.setPassword(encodedPassword);
			/*Step5 set user role as HR*/
			user.setRole(Role.CUSTOMER);
			user = userService.insert(user);
			
			return customerService.insert(customer);
			
		}
		
		@GetMapping("/customer/getall")
		public List<Customer> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
				@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size)
		{
			Pageable pageable = PageRequest.of(page,size);
			return customerService.getAll(pageable);
			
			
			
			}
		
		@GetMapping("/customer/getone/{cid}")
		public ResponseEntity<?> getOne(@PathVariable("cid") int cid) {
			try {
				Customer customer = customerService.getById(cid);
				return ResponseEntity.ok().body(customer);
			} catch (InvalidIdException e) {
				// TODO Auto-generated catch block
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		}
		
		@PutMapping("/customer/update/{cid}")
		public ResponseEntity<?> updateCustomer(@PathVariable("cid") int cid, @RequestBody Customerdto newCustomer) {
			try {
			Customer oldCustomer = customerService.getById(cid);
			if (newCustomer.getName() != null)
				oldCustomer.setName(newCustomer.getName());
			if (newCustomer.getPhone() != null)
				oldCustomer.setPhone(newCustomer.getPhone());
			if (newCustomer.getDateOfBirth() != null)
				oldCustomer.setDateOfBirth(newCustomer.getDateOfBirth());
			if (newCustomer.getAge() != 0)
				oldCustomer.setAge(newCustomer.getAge());
			if (newCustomer.getGender() != null)
				oldCustomer.setGender(newCustomer.getGender());
			if (newCustomer.getEmail() != null)
				oldCustomer.setEmail(newCustomer.getEmail());
			if (newCustomer.getIdproof() != null)
				oldCustomer.setIdProof(newCustomer.getIdproof());
			oldCustomer = customerService.insert(oldCustomer);
			return ResponseEntity.ok().body(oldCustomer);
		}
		catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		}
		
		@GetMapping("/customer/get/{uid}")
		public ResponseEntity<?> getOneUser(@PathVariable("uid") int uid) {
			Customer customer = customerService.getByUserId(uid);
			return ResponseEntity.ok().body(customer);
			
		}
		
	}

