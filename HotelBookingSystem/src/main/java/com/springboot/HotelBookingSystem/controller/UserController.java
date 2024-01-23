package com.springboot.HotelBookingSystem.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.CustomerRoom;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.CustomerRoomService;
import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.service.HotelService;
import com.springboot.HotelBookingSystem.service.UserService;



@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private CustomerRoomService customerRoomService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/auth/login")
	public User login(Principal principal) {
		String username = principal.getName();
		User user = (User)userService.loadUserByUsername(username);
		return user;
	}
	
//	@PostMapping("/user/add")
//	public User addUser(@RequestBody User user) {
//		user = userService.addUser(user);
//		return user;
//	}
	/*display the list of customers that have stayed in a particular hotel on a given date. 
	 * take hotelId as path variable along with date */
	
	@GetMapping("/customers/{hid}/{date}")
	public ResponseEntity<?> getCustomerByHidandDate(@PathVariable("hid") int hid,@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate date) {
		try {
			Hotel hotel = hotelService.getByHotelId(hid);
			List<CustomerRoom> customer = customerRoomService.getByDateandHotel(hid,date);
			return ResponseEntity.ok().body(customer);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
