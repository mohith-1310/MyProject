package com.springboot.HotelBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.dto.CustomerFamilyDto;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.CustomerFamily;
import com.springboot.HotelBookingSystem.service.CustomerFamilyService;
import com.springboot.HotelBookingSystem.service.CustomerService;

@RestController
@RequestMapping("/feelhome")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CustomerFamilyController {
	

		@Autowired
		private CustomerFamilyService customerFamilyService;
		
		@Autowired
		private CustomerService customerService;
		
		@PostMapping("/family/add/{cid}")
		public ResponseEntity<?> insertFamily( @PathVariable("cid") int cid,
									@RequestBody CustomerFamily family) {
			
			try {
				/*fetch customer details by ID*/
				Customer customer = customerService.getById(cid);
				/*set customer ID to family*/
				family.setCustomer(customer);
				/*insert customer family into db*/
				family = customerFamilyService.insert(family);
				return ResponseEntity.ok().body(family);
				
			} catch (InvalidIdException e) {
				// TODO Auto-generated catch block
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
			
		}
		
		@GetMapping("/family/getall")
		public List<CustomerFamily> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
				@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {
			Pageable pageable = PageRequest.of(page, size);
			return customerFamilyService.getAll(pageable);

		}

		@PutMapping("/family/update/{fid}")
		public ResponseEntity<?> updateFamily(@PathVariable("fid") int fid, @RequestBody CustomerFamilyDto newFamily) {
			try {
			CustomerFamily oldFamily = customerFamilyService.getById(fid);
			if (newFamily.getName() != null)
				oldFamily.setName(newFamily.getName());
			if (newFamily.getIdProof() != null)
				oldFamily.setIdProof(newFamily.getIdProof());

			oldFamily = customerFamilyService.insert(oldFamily);
			return ResponseEntity.ok().body(oldFamily);
		}
		catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		}
	}

