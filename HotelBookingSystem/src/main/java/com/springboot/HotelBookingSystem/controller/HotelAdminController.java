package com.springboot.HotelBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.dto.HotelAdminDto;
import com.springboot.HotelBookingSystem.enumerate.Role;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.HotelAdmin;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.ExecutiveService;
import com.springboot.HotelBookingSystem.service.HotelAdminService;
import com.springboot.HotelBookingSystem.service.UserService;

@RestController
@RequestMapping("/feelhome")
@CrossOrigin(origins = {"http://localhost:3000"})
public class HotelAdminController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private HotelAdminService hotelAdminService;

	@Autowired
	private ExecutiveService executiveService;

	@PostMapping("/admin/add/{eid}")
	public ResponseEntity<?> postAdmin(@PathVariable("eid") int eid, @RequestBody HotelAdmin hotelAdmin) {

		// Getting executive details by Executive Id
		try {
			Executive executive = executiveService.getById(eid);

			// Getting user details from postman
			User user = hotelAdmin.getUser();
			String passwordPlain = user.getPassword();
			String encodedPassword = passwordEncoder.encode(passwordPlain);
			user.setPassword(encodedPassword);
			user.setRole(Role.HOTEL_ADMIN);

			// inserting Admin details in user table
			user = userService.insert(user);

			hotelAdmin.setUser(user);
			hotelAdmin.setExecutive(executive);

			// Inserting admin details in hotelAdmin table
			hotelAdmin = hotelAdminService.postAdmin(hotelAdmin);
			return ResponseEntity.ok().body(hotelAdmin);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("hoteladmin/delete/{id}")
	public ResponseEntity<?> deleteHotelAdmin(@PathVariable("id") int id) {

		try {
			// validate id
			HotelAdmin hotelAdmin = hotelAdminService.getOne(id);

			// delete id
			hotelAdminService.deleteHotelAdmin(hotelAdmin);

			return ResponseEntity.ok().body("admin deleted successfully");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("hoteladmin/get/{eid}")
	public ResponseEntity<?> getByExecutive(@PathVariable("eid") int eid) {
//		fetch executive details by id
		try {
			Executive executive = executiveService.getById(eid);
//			list declaration
			List<HotelAdmin> list = hotelAdminService.getByExecutive(eid);

			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/updatehoteladmin/{aid}")
	public ResponseEntity<?> updateHotelAdmin(@PathVariable("aid") int aid, @RequestBody HotelAdminDto newHotelAdmin) {
		try {
			HotelAdmin oldHotelAdmin = hotelAdminService.getAdminById(aid);
			if (newHotelAdmin.getName() != null)
				oldHotelAdmin.setName(newHotelAdmin.getName());
			if (newHotelAdmin.getEmail() != null)
				oldHotelAdmin.setEmail(newHotelAdmin.getEmail());
			if (newHotelAdmin.getExecutive() != null)
				oldHotelAdmin.setExecutive(newHotelAdmin.getExecutive());

			oldHotelAdmin = hotelAdminService.insertRoom(oldHotelAdmin);

			return ResponseEntity.ok().body(oldHotelAdmin);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/hoteladmin/getone/{uid}")
	public ResponseEntity<?> getOneUser(@PathVariable("uid") int uid) {
		HotelAdmin hotelAdmin = hotelAdminService.getByUserId(uid);
		return ResponseEntity.ok().body(hotelAdmin);
		
	}
	
	@GetMapping("/admin/getall")
	public List<HotelAdmin> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size)
	{
		Pageable pageable = PageRequest.of(page,size);
		return hotelAdminService.getAll(pageable);
		
		
		
		}
}
